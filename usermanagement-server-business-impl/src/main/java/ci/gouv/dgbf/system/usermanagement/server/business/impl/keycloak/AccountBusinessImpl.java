package ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak;

import java.io.Serializable;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.cyk.utility.__kernel__.properties.Properties;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.account.AbstractAccountBusinessImpl;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

@Keycloak
public class AccountBusinessImpl extends AbstractAccountBusinessImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override @Transactional
	public AccountBusiness create(Account account, Properties properties) {
		UserRepresentation user = new UserRepresentation();
		user.setEnabled(Boolean.TRUE);
		user.setUsername(account.getCode());
		
		UsersResource userRessource = __inject__(KeycloakHelper.class).getClient().realm("sib").users();

		Response response = userRessource.create(user);
		String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
		
		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue(account.getPass());
		userRessource.get(userId).resetPassword(passwordCred);
		
		return this;
	}
	
	@Override
	public AccountBusiness delete(Account account, Properties properties) {
		UsersResource userRessource = __inject__(KeycloakHelper.class).getClient().realm("sib").users();		
		UserRepresentation userRepresentation = __injectCollectionHelper__().getFirst(userRessource.search(account.getCode()));
		userRessource.delete(userRepresentation.getId());
		return this;
	}
	
}
