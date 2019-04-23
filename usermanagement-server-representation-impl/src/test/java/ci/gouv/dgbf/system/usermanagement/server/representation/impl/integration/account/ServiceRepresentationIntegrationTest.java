package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.ServiceRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

public class ServiceRepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void read() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		@SuppressWarnings("unchecked")
		Collection<ServiceDto> roleDtos = (Collection<ServiceDto>) __inject__(ServiceRepresentation.class).getMany(null, null, null).getEntity();
		assertThat(roleDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("WORKFLOW");
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
