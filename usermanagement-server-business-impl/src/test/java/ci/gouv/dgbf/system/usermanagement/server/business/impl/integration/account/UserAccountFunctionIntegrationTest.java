package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserNaturalPerson;

public class UserAccountFunctionIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneUserAccount() throws Exception{
		/*User user = new User().setElectronicMailAddress("kycdev@gmail.com").setPerson(new UserNaturalPerson().setFirstName("Zadi").setLastNames("Business"));
		UserAccount userAccount = new UserAccount().setUser(user);
		__inject__(TestBusinessCreate.class).addObjects(userAccount).execute();
		*/
	}
	

}
