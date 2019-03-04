package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserNaturalPersonDto;

public class UserAccountFunctionIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void createOneUserAccount() throws Exception{
		UserDto user = new UserDto().setElectronicMailAddress("kycdev@gmail.com").setPerson(new UserNaturalPersonDto().setFirstName("Zadi").setLastNames("Representation"));
		UserAccountDto userAccount = new UserAccountDto().setCode("ua01").setUser(user);
		__inject__(TestRepresentationCreate.class).addObjects(userAccount).execute();
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
