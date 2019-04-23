package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;

public class UserAccountPersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneUserAccount() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress("kycdev@gmail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier("username").setPass("123");
		__inject__(TestPersistenceCreate.class).addObjects(userAccount).execute();
	}
	
}
