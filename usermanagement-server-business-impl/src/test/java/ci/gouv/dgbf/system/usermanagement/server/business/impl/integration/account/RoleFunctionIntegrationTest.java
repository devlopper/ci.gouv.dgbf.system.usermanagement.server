package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;

public class RoleFunctionIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneRole() throws Exception{
		RoleType type = new RoleType().setCode(__getRandomCode__()).setName(__getRandomCode__());
		Role role = new Role().setCode(__getRandomCode__()).setName(__getRandomCode__()).setType(type);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(type).addObjects(role).execute();
	}
	

}
