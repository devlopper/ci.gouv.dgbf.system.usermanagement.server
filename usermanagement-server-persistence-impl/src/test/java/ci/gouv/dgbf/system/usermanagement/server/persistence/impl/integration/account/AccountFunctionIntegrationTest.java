package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class AccountFunctionIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneAccount() throws Exception{
		/*String code = __getRandomCode__();
		String pass = __getRandomString__();
		Account account = new Account().setCode(code).setPass(pass);
		__inject__(TestPersistenceCreate.class).addObjects(account).execute();
		*/
	}
	
	@Test
	public void readAccountByCodeByPass() throws Exception{
		/*String code = __getRandomCode__();
		String pass = __getRandomString__();
		Account account = null;
		account = __inject__(AccountPersistence.class).readByCodeByPass(code, pass);
		assertionHelper.assertNull("account with code<<"+code+">> and pass <<"+pass+">> found",account);
		
		account = new Account().setCode(code).setPass(pass);
		userTransaction.begin();
		__inject__(AccountPersistence.class).create(account);
		userTransaction.commit();
		
		account = __inject__(AccountPersistence.class).readByCodeByPass(code, pass);
		assertionHelper.assertNotNull("account with code<<"+code+">> and pass <<"+pass+">> not found",account);
		*/
	}
}
