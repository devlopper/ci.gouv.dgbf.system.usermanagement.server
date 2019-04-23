package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;

public class RoleBusinessIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneRole() throws Exception{
		RoleType type = new RoleType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Role role = new Role().setCode(__getRandomCode__()).setName(__getRandomCode__()).setType(type);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(type).addObjects(role).execute();
	}
	
	@Test
	public void findOneRoleByBusinessIdentifier() throws Exception{
		Role role = __inject__(RoleBusiness.class).findOne("ADMINISTRATIF", new Properties());
		assertThat(role.getCode()).isEqualTo("ADMINISTRATIF");
	}
}
