package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDto;

public class RoleFunctionRepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void read() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RoleFunctionDto> roleFunctionDtos = (Collection<RoleFunctionDto>) __inject__(RoleFunctionRepresentation.class).getMany(null, null, null).getEntity();
		assertThat(roleFunctionDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("RP","DIRECTEUR");
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
