package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration;

import java.util.Collection;

import org.cyk.utility.__kernel__.string.RegularExpressionHelper;
import org.junit.Test;
import org.keycloak.representations.idm.UserRepresentation;

import static org.assertj.core.api.Assertions.assertThat;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;

public class RepresentationIntegrationTestKeycloak extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		__inject__(KeycloakHelper.class).deleteAllUsers(RegularExpressionHelper.buildIsNotExactly("admin"));
	}
	
	@Test
	public void importFromKeycloak(){
		__inject__(KeycloakHelper.class).createUserAccount(null, null, null, "u01", null, null, null);
		__inject__(KeycloakHelper.class).createUserAccount("Komenan", "Yao Christian", "kycdev@gmail.com", "cyk", "mypass", null, null);
		__inject__(KeycloakHelper.class).createUserAccount("Yao", "Constant", "m@mail.com", "yao", "123", null, null);
		__inject__(UserAccountRepresentation.class).importFromKeycloak();
		@SuppressWarnings("unchecked")
		Collection<UserAccountDto> userAccounts = (Collection<UserAccountDto>) __inject__(UserAccountRepresentation.class).getMany(null,Boolean.FALSE, null, null, null, null).getEntity();
		assertThat(userAccounts).isNotNull();
		assertThat(userAccounts.stream().map(x -> x.getAccount().getIdentifier())).containsExactlyInAnyOrder("u01","cyk","yao");		
	}

	@Test
	public void exportToKeycloak(){
		__inject__(UserAccountBusiness.class).create(new UserAccount().setUser(new User().setFirstName("konan")).setAccount(new Account().setIdentifier("u02").setPass("789")));
		__inject__(UserAccountRepresentation.class).exportToKeycloak();
		UserRepresentation userRepresentation = __inject__(KeycloakHelper.class).getUserRepresentationByUserName("u02");
		assertThat(userRepresentation).isNotNull();		
	}
}
