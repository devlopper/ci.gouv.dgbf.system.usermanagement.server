package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.Keycloak;

public class RolePostePersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void readRolePostes() throws Exception{
		DependencyInjection.setQualifierClass(RolePostePersistence.class, Keycloak.Class.class);
		Collection<RolePoste> rolePostes = __inject__(RolePostePersistence.class).read();
		assertThat(rolePostes.stream().map(x -> x.getRole().getCode()).collect(Collectors.toList())).contains("AS_MIN_21");
	}
}
