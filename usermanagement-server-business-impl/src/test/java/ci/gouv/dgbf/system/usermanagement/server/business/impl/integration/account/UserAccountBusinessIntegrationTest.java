package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;

public class UserAccountBusinessIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneUserAccount() throws Exception{
		startServersZookeeperAndKafka();
		
		__inject__(TimeHelper.class).pause(1000l * 15);
		
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		
		__inject__(TimeHelper.class).pause(1000l * 15);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress("kycdev@gmail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRoles(__inject__(RolePersistence.class).readOneByBusinessIdentifier("CE"));
		__inject__(TestBusinessCreate.class).addObjects(userAccount).execute();
		
		__inject__(TimeHelper.class).pause(1000l * 15);
		
		stopServersKafkaAndZookeeper();	
	}
	

}
