package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDtoCollection;

@ApplicationScoped
public class UserAccountRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccount,UserAccountBusiness,UserAccountDto,UserAccountDtoCollection> implements UserAccountRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Response importFromKeycloak() {
		Collection<UserRepresentation> userRepresentations = __inject__(KeycloakHelper.class).getUsersResource().list();
		if(CollectionHelper.isEmpty(userRepresentations))
			return Response.ok("nothing to import").build();
		Collection<UserAccount> userAccounts = null;		
		for(UserRepresentation index : userRepresentations) {
			if(index.getUsername().equals("admin"))
				continue;			
			Account account = __inject__(AccountPersistence.class).readBySystemIdentifier(index.getUsername());
			if(account != null)
				continue;			
			UserAccount userAccount = new UserAccount().setIsPersistToKeycloakOnCreate(Boolean.FALSE);
			userAccount.getUser(Boolean.TRUE).setFirstName(index.getFirstName());
			userAccount.getUser(Boolean.TRUE).setLastNames(index.getLastName());
			userAccount.getUser(Boolean.TRUE).setElectronicMailAddress(index.getEmail());
			userAccount.getAccount(Boolean.TRUE).setIdentifier(index.getUsername());
			userAccount.setIsPersistToKeycloakOnCreate(Boolean.FALSE);
			if(userAccounts == null)
				userAccounts = new ArrayList<>();
			userAccounts.add(userAccount);							
		}
		if(CollectionHelper.isNotEmpty(userAccounts))
			__inject__(UserAccountBusiness.class).createMany(userAccounts);
		return Response.ok(CollectionHelper.getSize(userRepresentations)+" found and "+CollectionHelper.getSize(userAccounts)+" imported").build();
	}
	
	@Override
	public Response exportToKeycloak() {
		Collection<UserAccount> userAccounts = __inject__(UserAccountPersistence.class).read();
		if(CollectionHelper.isEmpty(userAccounts))
			return Response.ok("nothing to export").build();	
		Collection<UserAccount> userAccountsToBeExported = null;
		for(UserAccount index : userAccounts) {
			UserRepresentation userRepresentation = __inject__(KeycloakHelper.class).getUserRepresentationByUserName(index.getAccount().getIdentifier());
			if(userRepresentation != null)
				continue;
			if(userAccountsToBeExported == null)
				userAccountsToBeExported = new ArrayList<>();
			userAccountsToBeExported.add(index);							
		}
		if(CollectionHelper.isNotEmpty(userAccountsToBeExported))
			__inject__(KeycloakHelper.class).createUserAccounts(userAccountsToBeExported);
		return Response.ok(CollectionHelper.getSize(userAccounts)+" found and "+CollectionHelper.getSize(userAccountsToBeExported)+" exported").build();
	}
}
