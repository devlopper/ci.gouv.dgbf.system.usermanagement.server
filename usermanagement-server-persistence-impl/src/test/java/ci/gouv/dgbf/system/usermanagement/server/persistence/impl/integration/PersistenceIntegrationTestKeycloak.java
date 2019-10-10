package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.cyk.utility.__kernel__.string.RegularExpressionHelper;
import org.junit.Test;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

public class PersistenceIntegrationTestKeycloak extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		__inject__(KeycloakHelper.class).deleteAllUsers(RegularExpressionHelper.buildIsNotExactly("admin"));
	}
	
	@Test
	public void create_userAccount() throws Exception{
		UserAccount userAccount = new UserAccount().setIsPersistToKeycloakOnCreate(Boolean.TRUE);
		String identifier = __getRandomIdentifier__();
		userAccount.setIdentifier(identifier);
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__().toLowerCase());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userTransaction.begin();
		__inject__(UserPersistence.class).create(userAccount.getUser());
		__inject__(AccountPersistence.class).create(userAccount.getAccount());
		__inject__(UserAccountPersistence.class).create(userAccount);
		userTransaction.commit();
		UserRepresentation userRepresentation = __inject__(KeycloakHelper.class).getUserRepresentationByUserName(userAccount.getAccount().getIdentifier());
		assertThat(userRepresentation).isNotNull();
		assertThat(userRepresentation.getFirstName()).isEqualTo(userAccount.getUser().getFirstName());
		assertThat(userRepresentation.getLastName()).isEqualTo(userAccount.getUser().getLastNames());
		assertThat(userRepresentation.getEmail()).isEqualTo(userAccount.getUser().getElectronicMailAddress());
	}
	
}
