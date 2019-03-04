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
		__inject__(TestBusinessCreate.class).addObjects(account).execute();
	}
	
	@Test
	public void findAccountByCodeByPass() throws Exception{
		String code = __getRandomCode__();
		String pass = __getRandomString__();
		Account account = null;
		account = __inject__(AccountBusiness.class).findByCodeByPass(code, pass);
		assertionHelper.assertNull("account with code<<"+code+">> and pass <<"+pass+">> found",account);
		
		account = new Account().setCode(code).setPass(pass);
		__inject__(AccountBusiness.class).create(account);
				
		account = __inject__(AccountBusiness.class).findByCodeByPass(code, pass);
		assertionHelper.assertNotNull("account with code<<"+code+">> and pass <<"+pass+">> not found",account);
	}
	
	@Test
	public void authenticate_success() throws Exception{
		String code = __getRandomCode__();
		String pass = __getRandomString__();
		Account account = null;
		
		account = new Account().setCode(code).setPass(pass);
		__inject__(AccountBusiness.class).create(account);
				
		account = __inject__(AccountBusiness.class).authenticate(code, pass);
		assertionHelper.assertNotNull("account with code<<"+code+">> and pass <<"+pass+">> not found",account);
	}
	
	@Test(expected=RuntimeException.class)
	public void authenticate_throwable_code_or_pass_not_found() throws Exception{
		String code = __getRandomCode__();
		String pass = __getRandomString__();
		__inject__(AccountBusiness.class).authenticate(code, pass);
	}
	
}
