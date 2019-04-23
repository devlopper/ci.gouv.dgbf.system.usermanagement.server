package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;

public class RoleTypeBusinessIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneRoleType() throws Exception{
		RoleType roleType = new RoleType().setCode(__getRandomCode__()).setName(__getRandomCode__());
		__inject__(TestBusinessCreate.class).addObjects(roleType).execute();			
	}
	
}
