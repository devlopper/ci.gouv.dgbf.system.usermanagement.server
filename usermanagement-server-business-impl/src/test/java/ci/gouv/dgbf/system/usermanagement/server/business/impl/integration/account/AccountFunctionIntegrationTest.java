package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

public class AccountFunctionIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneAccount() throws Exception{
		String code = __getRandomCode__();
		String pass = __getRandomString__();
		Account account = new Account().setCode(code).setPass(pass);
		__inject__(AccountBusiness.class).create(account);
		__inject__(AccountBusiness.class).delete(account);
		//__inject__(TestBusinessCreate.class).addObjects(account).execute();
	}
	
	
	
}
