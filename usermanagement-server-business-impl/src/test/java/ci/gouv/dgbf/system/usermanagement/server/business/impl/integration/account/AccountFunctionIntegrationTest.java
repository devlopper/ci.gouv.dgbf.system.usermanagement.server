package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import org.cyk.utility.__kernel__.annotation.Default;
import org.cyk.utility.__kernel__.test.arquillian.archive.builder.WebArchiveBuilder;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTest;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.Producer;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak.Keycloak;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

public class AccountFunctionIntegrationTest extends AbstractBusinessArquillianIntegrationTest {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneAccount() throws Exception{
		String code = __getRandomCode__();
		String pass = __getRandomString__();
		Account account = new Account().setCode(code).setPass(pass);
		Class<?> singleSignOnQualifierClass = __inject__(Producer.class).getSingleSignOnQualifierClass();
		if(singleSignOnQualifierClass == null || Default.class.equals(singleSignOnQualifierClass)) {
			__inject__(TestBusinessCreate.class).addObjects(account).execute();
		}else if(Keycloak.class.equals(singleSignOnQualifierClass)) {
			__inject__(AccountBusiness.class).create(account);
			__inject__(AccountBusiness.class).delete(account);	
		}
	}
	
	@org.jboss.arquillian.container.test.api.Deployment
	public static WebArchive createArchive(){
		return new WebArchiveBuilder().execute(); 
	}
	
}
