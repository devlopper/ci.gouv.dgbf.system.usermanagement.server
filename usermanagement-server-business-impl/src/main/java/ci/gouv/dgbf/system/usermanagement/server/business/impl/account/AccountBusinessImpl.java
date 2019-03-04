package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.Arrays;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessServiceProvider;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

@Singleton
public class AccountBusinessImpl extends AbstractBusinessEntityImpl<Account, AccountPersistence> implements AccountBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Account> __getPersistenceEntityClass__() {
		return Account.class;
	}
	
	@Override @Transactional
	public AccountBusiness create(Account account, Properties properties) {
		//Create account using keycloak API
		String realm = "master";
		
		Keycloak keycloak = KeycloakBuilder.builder().serverUrl("http://localhost:8230/auth").realm(realm).grantType(OAuth2Constants.PASSWORD).clientId("admin-cli") //
				.clientSecret("5187b082-3b86-40bc-ba78-1a447c269dd9").username("keycloack").password("keycloack").build();

		// Define user
		UserRepresentation user = new UserRepresentation();
		user.setEnabled(Boolean.TRUE);
		user.setUsername(account.getCode());
		/*user.setFirstName("First");
		user.setLastName("Last");
		user.setEmail("kycdev@gmail.com");
		*/
		//user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

		// Get realm
		RealmResource realmResource = keycloak.realm(realm);
		UsersResource userRessource = realmResource.users();

		Response response = userRessource.create(user);
		String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
		RoleRepresentation testerRealmRole = realmResource.roles().get("myrole").toRepresentation();
		userRessource.get(userId).roles().realmLevel().add(Arrays.asList(testerRealmRole));

		ClientRepresentation app1Client = realmResource.clients().findByClientId("demo-realm").get(0);
		RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()).roles().get("mycustomrole").toRepresentation();
		userRessource.get(userId).roles().clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));

		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue(account.getPass());
		userRessource.get(userId).resetPassword(passwordCred);
		
		return this;
	}
	
	@Override
	public AccountBusiness delete(Account account, Properties properties) {
		String realm = "master";
		
		Keycloak keycloak = KeycloakBuilder.builder().serverUrl("http://localhost:8230/auth").realm(realm).grantType(OAuth2Constants.PASSWORD).clientId("admin-cli") //
				.clientSecret("5187b082-3b86-40bc-ba78-1a447c269dd9").username("keycloack").password("keycloack").build();
		
		// Get realm
		RealmResource realmResource = keycloak.realm(realm);
		UsersResource userRessource = realmResource.users();		
		UserRepresentation userRepresentation = __injectCollectionHelper__().getFirst(userRessource.search(account.getCode()));
		userRessource.delete(userRepresentation.getId());
		
		return this;
	}
	
}
