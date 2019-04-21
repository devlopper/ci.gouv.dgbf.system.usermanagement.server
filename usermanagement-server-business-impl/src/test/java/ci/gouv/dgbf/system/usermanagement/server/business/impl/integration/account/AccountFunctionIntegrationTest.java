package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

public class AccountFunctionIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneAccount() throws Exception{
		/*String code = __getRandomCode__();
		String pass = __getRandomString__();
		Account account = new Account().setCode(code).setPass(pass);
		Class<?> singleSignOnQualifierClass = __inject__(Producer.class).getSingleSignOnQualifierClass();
		if(singleSignOnQualifierClass == null || Default.class.equals(singleSignOnQualifierClass)) {
			__inject__(TestBusinessCreate.class).addObjects(account).execute();
		}else if(Keycloak.class.equals(singleSignOnQualifierClass)) {
			__inject__(AccountBusiness.class).create(account);
			__inject__(AccountBusiness.class).delete(account);	
		}
		*/
	}
	
}
