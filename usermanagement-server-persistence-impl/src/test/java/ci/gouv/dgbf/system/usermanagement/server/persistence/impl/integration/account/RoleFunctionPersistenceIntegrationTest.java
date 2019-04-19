package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.Keycloak;

public class RoleFunctionPersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void readRoleFunctions() throws Exception{
		DependencyInjection.setQualifierClass(RoleFunctionPersistence.class, Keycloak.Class.class);
		Collection<RoleFunction> roleFunctions = __inject__(RoleFunctionPersistence.class).read();
		assertThat(roleFunctions.stream().map(x -> x.getRole().getCode()).collect(Collectors.toList())).contains("RP","RBOP","AS","AC","CB","CF","DIRECTEUR");
	}
}
