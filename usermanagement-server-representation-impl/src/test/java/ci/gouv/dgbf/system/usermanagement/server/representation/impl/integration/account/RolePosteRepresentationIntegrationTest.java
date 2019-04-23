package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDto;

public class RolePosteRepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void read() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RolePosteDto> rolePosteDtos = (Collection<RolePosteDto>) __inject__(RolePosteRepresentation.class).getMany(null, null, null).getEntity();
		assertThat(rolePosteDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("AS_MIN_21","CF_MIN_21");
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
