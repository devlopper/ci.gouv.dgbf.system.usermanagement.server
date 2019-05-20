package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.value.ValueUsageType;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractUserAccountPersistenceImpl;

@Singleton @Keycloak
public class UserAccountPersistenceImpl extends AbstractUserAccountPersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public PersistenceServiceProvider<UserAccount> create(UserAccount userAccount, Properties properties) {
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setEnabled(true);
		userRepresentation.setUsername(userAccount.getAccount().getIdentifier());
		userRepresentation.setFirstName(userAccount.getUser().getFirstName());
		userRepresentation.setLastName(userAccount.getUser().getLastNames());
		userRepresentation.setEmail(userAccount.getUser().getElectronicMailAddress());
		
		// Get realm
		//RealmResource realmResource = __inject__(KeycloakHelper.class).getRealmResource();
		UsersResource usersRessource = __inject__(KeycloakHelper.class).getUsersResource();

		Response response = usersRessource.create(userRepresentation);
		String userIdentifier = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
		userAccount.setIdentifier(userIdentifier);
		UserResource userResource = usersRessource.get(userIdentifier);
		
		/*Collection<Role> roles = userAccount.getRoles();
		if(roles != null) 
			roles.forEach(new Consumer<Role>() {
				@Override
				public void accept(Role role) {
					RoleRepresentation roleRepresentation = realmResource.roles().get(role.getCode()).toRepresentation();
					userResource.roles().realmLevel().add(Arrays.asList(roleRepresentation));
				}
			});*/

		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation.setTemporary(Boolean.TRUE);
		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
		credentialRepresentation.setValue(__injectValueHelper__().defaultToIfNull(userAccount.getAccount().getPass(), "1234"));
		userResource.resetPassword(credentialRepresentation);
				
		return this;
	}
	
	@Override
	public Collection<UserAccount> read(Properties properties) {
		Collection<UserAccount> userAccounts = new ArrayList<>();
		Collection<UserRepresentation> userRepresentations = __inject__(KeycloakHelper.class).getUsersResource().list();
		for(UserRepresentation index : userRepresentations)
			userAccounts.add(__instanciate__(index));
		return userAccounts;
	}
	
	@Override
	public UserAccount readOne(Object identifier, Properties properties) {
		ValueUsageType valueUsageType = (ValueUsageType) __injectValueHelper__().defaultToIfNull(Properties.getFromPath(properties, Properties.VALUE_USAGE_TYPE),ValueUsageType.BUSINESS);	
		UsersResource usersResource = __inject__(KeycloakHelper.class).getUsersResource();
		String identifierAsString = (String) identifier;
		UserRepresentation userRepresentation = ValueUsageType.SYSTEM.equals(valueUsageType) ? usersResource.get(identifierAsString) .toRepresentation() 
				: __injectCollectionHelper__().getFirst(usersResource.search(identifierAsString));
		return __instanciate__(userRepresentation);
	}
	
	public static UserAccount __instanciate__(UserRepresentation userRepresentation) {
		UserAccount userAccount = null;
		if(userRepresentation != null) {
			userAccount = new UserAccount()
					.setIdentifier(userRepresentation.getId())
					.setUser(new User().setFirstName(userRepresentation.getFirstName()).setLastNames(userRepresentation.getLastName()))
					.setAccount(new Account().setIdentifier(userRepresentation.getUsername()))
					;
		}
		return userAccount;
	}
	
	public static Collection<UserAccount> __instanciate__(Collection<UserRepresentation> userRepresentations) {
		return userRepresentations == null ? null : userRepresentations.stream().map(x -> __instanciate__(x)).collect(Collectors.toList());
	}
	
	@Override
	public PersistenceServiceProvider<UserAccount> update(UserAccount userAccount, Properties properties) {
		UsersResource usersResource = __inject__(KeycloakHelper.class).getUsersResource();
		UserResource userResource = usersResource.get(userAccount.getIdentifier());
		UserRepresentation userRepresentation = userResource.toRepresentation();
		userRepresentation.setUsername(userAccount.getAccount().getIdentifier());
		userRepresentation.setFirstName(userAccount.getUser().getFirstName());
		userRepresentation.setLastName(userAccount.getUser().getLastNames());
		userRepresentation.setEmail(userAccount.getUser().getElectronicMailAddress());
		userResource.update(userRepresentation);
		
		try{
			//updateAttributes(service, clientsResource.get(service.getIdentifier()));
		}catch(NotFoundException exception) {
			
		}
		return this;
	}

	@Override
	public PersistenceServiceProvider<UserAccount> delete(UserAccount userAccount, Properties properties) {
		try{
			__inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier()).remove();
		}catch(NotFoundException exception) {
			
		}
		return this;
	}
	
	@Override
	public Long count(Properties arg0) {
		return null;
	}
}
