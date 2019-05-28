package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;

public class BusinessIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
		super.__listenBeforeCallCountIsZero__();
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Test
	public void create_roleCategory() throws Exception{
		RoleCategory role = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomCode__());
		__inject__(TestBusinessCreate.class).addObjects(role).execute();
	}
	
	@Test
	public void find_roleCategoryByBusinessIdentifier() throws Exception{
		RoleCategory role = __inject__(RoleCategoryBusiness.class).findOneByBusinessIdentifier("ADMINISTRATIF");
		assertThat(role.getCode()).isEqualTo("ADMINISTRATIF");
	}
	
	@Test
	public void count_roleCategory() throws Exception{
		Long count = __inject__(RoleCategoryBusiness.class).count();
		assertThat(count).isEqualTo(2);
	}
	
	@Test
	public void create_roleFunction() throws Exception{
		RoleFunction role = new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomCode__())
				.setCategory(__inject__(RoleCategoryBusiness.class).findOneByBusinessIdentifier("ADMINISTRATIF"));
		__inject__(TestBusinessCreate.class).addObjects(role).execute();
	}
	
	@Test
	public void find_roleFunctionByBusinessIdentifier() throws Exception{
		RoleFunction role = __inject__(RoleFunctionBusiness.class).findOneByBusinessIdentifier("ASSISTANT");
		assertThat(role.getCode()).isEqualTo("ASSISTANT");
		assertThat(role.getCategory()).isNotNull();
		assertThat(role.getCategory().getCode()).isEqualTo("ADMINISTRATIF");
	}
	
	@Test
	public void count_roleFunction() throws Exception{
		Long count = __inject__(RoleFunctionBusiness.class).count();
		assertThat(count).isEqualTo(16);
	}
	
	@Test
	public void count_roleFunction_from5() throws Exception{
		Properties properties = new Properties();
		properties.setFilters(null).setIsQueryResultPaginated(Boolean.TRUE).setQueryFirstTupleIndex(5).setQueryNumberOfTuple(5).setQueryIdentifier(null);
		Long count = __inject__(RoleFunctionBusiness.class).count(properties);
		assertThat(count).isEqualTo(16);
	}
	
	@Test
	public void create_rolePoste() throws Exception{
		RolePoste role = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomCode__())
				.setFunction(__inject__(RoleFunctionBusiness.class).findOneByBusinessIdentifier("ASSISTANT"));
		__inject__(TestBusinessCreate.class).addObjects(role).execute();
	}
	
	@Test
	public void find_rolePosteByBusinessIdentifier() throws Exception{
		RolePoste role = __inject__(RolePosteBusiness.class).findOneByBusinessIdentifier("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(role.getCode()).isEqualTo("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(role.getFunction()).isNotNull();
		assertThat(role.getFunction().getCode()).isEqualTo("CONTROLEUR_FINANCIER");
	}
	
	@Test
	public void create_service() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		Service service = new Service().setIdentifier(__getRandomCode__());
		__inject__(TestBusinessCreate.class).addObjects(service).execute();
	}
	
	/*@Test
	public void find_serviceByBusinessIdentifier() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		Service service = __inject__(ServiceBusiness.class).findOne("WORKFLOW", new Properties().setValueUsageType(ValueUsageType.BUSINESS));
		assertThat(service.getCode()).isEqualTo("WORKFLOW");
	}*/
	
	@Test
	public void create_userAccount() throws Exception{
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			startServersZookeeperAndKafka();
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress("kycdev@gmail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		//userAccount.addRolePostes(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier("CONTROLEUR_FINANCIER_MINISTERE_21"));
		__inject__(TestBusinessCreate.class).addObjects(userAccount).execute();
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}else {
			
		}
	}
}
