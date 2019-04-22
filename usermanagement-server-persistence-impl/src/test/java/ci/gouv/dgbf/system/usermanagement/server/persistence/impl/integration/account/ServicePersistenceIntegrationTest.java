package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.ServicePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;

public class ServicePersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneService() throws Exception{
		Service service = new Service().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjects(service).execute();
	}
	
	@Test
	public void read() throws Exception{
		Collection<Service> services = __inject__(ServicePersistence.class).read();
		assertThat(services.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("PLAN_ENGAGEMENT","AUTHENTIFICATION");
	}
	
	@Test
	public void readOneRoleByBusinessIdentifier() throws Exception{
		Service service = __inject__(ServicePersistence.class).readOneByBusinessIdentifier("PLAN_ENGAGEMENT");
		assertThat(service.getCode()).isEqualTo("PLAN_ENGAGEMENT");
	}
}
