package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDto;

public class RoleCategoryRepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void read() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RoleCategoryDto> roleCategoryDtos = (Collection<RoleCategoryDto>) __inject__(RoleCategoryRepresentation.class).getMany(null, null, null).getEntity();
		assertThat(roleCategoryDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
