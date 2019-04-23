package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.value.ValueUsageType;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.ServiceBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;

public class ServiceBusinessIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneService() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		Service service = new Service().setCode(__getRandomCode__()).setName(__getRandomCode__());
		__inject__(TestBusinessCreate.class).addObjects(service).execute();
	}
	
	@Test
	public void findOneRoleByBusinessIdentifier() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		Service service = __inject__(ServiceBusiness.class).findOne("WORKFLOW", new Properties().setValueUsageType(ValueUsageType.BUSINESS));
		assertThat(service.getCode()).isEqualTo("WORKFLOW");
	}
}
