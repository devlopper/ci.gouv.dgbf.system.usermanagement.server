package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;

public class RolePersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneRole() throws Exception{
		RoleType type = new RoleType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Role role = new Role().setType(type).setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(type).addObjects(role).execute();
	}
	
	@Test
	public void read() throws Exception{
		Collection<Role> roles = __inject__(RolePersistence.class).read();
		assertThat(roles.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE","AS","RBOP","DIRECTEUR","AS_MIN_21");
	}
	
	@Test
	public void readOneRoleByBusinessIdentifier() throws Exception{
		Role role = __inject__(RolePersistence.class).readOne("ADMINISTRATIF", new Properties());
		assertThat(role.getCode()).isEqualTo("ADMINISTRATIF");
	}
}
