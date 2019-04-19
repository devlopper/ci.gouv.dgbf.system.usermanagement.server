package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class UserAccountFunctionIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneUserAccount() throws Exception{
		/*User user = new User().setCode("u01").setElectronicMailAddress("kycdev@gmail.com");
		UserNaturalPerson person = new UserNaturalPerson().setCode("u01p01").setUser(user).setFirstName("Zadi");
		Account account = new Account().setCode("username").setPass("123");
		UserAccount userAccount = new UserAccount().setCode("ua01").setUser(user).setAccount(account);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(user,person,account).addObjects(userAccount).execute();
		*/
	}
	
}
