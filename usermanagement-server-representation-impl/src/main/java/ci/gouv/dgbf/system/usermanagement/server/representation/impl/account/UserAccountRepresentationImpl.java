package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
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
		Collection<UserAccount> userAccounts = new ArrayList<>();
		if(userRepresentations != null)
			for(UserRepresentation index : userRepresentations) {
				if(!index.getUsername().equals("admin")) {
					Account account = __inject__(AccountPersistence.class).readBySystemIdentifier(index.getUsername());
					if(account == null) {
						UserAccount userAccount = new UserAccount();
						userAccount.setIdentifier(index.getId());
						userAccount.getUser(Boolean.TRUE).setFirstName(index.getFirstName());
						userAccount.getUser(Boolean.TRUE).setLastNames(index.getLastName());
						userAccount.getUser(Boolean.TRUE).setElectronicMailAddress(index.getEmail());
						userAccount.getAccount(Boolean.TRUE).setIdentifier(index.getUsername());
						userAccount.setIsPersistToKeycloakOnCreate(Boolean.FALSE);
						userAccounts.add(userAccount);	
					}	
				}
			}
		__inject__(UserAccountBusiness.class).createMany(userAccounts);
		return Response.ok().build();
	}
	
	@Override
	public Class<UserAccount> getPersistenceEntityClass() {
		return UserAccount.class;
	}

}
