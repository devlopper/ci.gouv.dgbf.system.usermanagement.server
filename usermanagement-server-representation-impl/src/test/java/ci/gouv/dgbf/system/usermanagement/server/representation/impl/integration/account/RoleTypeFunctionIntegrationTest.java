package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleTypeDto;

public class RoleTypeFunctionIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void createOneRoleType() throws Exception{
		RoleTypeDto roleType = new RoleTypeDto().setCode(__getRandomCode__()).setName(__getRandomCode__());
		__inject__(TestRepresentationCreate.class).addObjects(roleType).execute();
	}
	
	@Test
	public void updateOneRoleType() throws Exception{
		String code = __getRandomCode__();
		RoleType roleType = new RoleType().setCode(code).setName("n01ToEdit");
		__inject__(RoleTypeBusiness.class).create(roleType);
		
		RoleTypeDto roleTypeDto = (RoleTypeDto) __inject__(RoleTypeRepresentation.class).getOne(code, "business",null).getEntity();
		assertionHelper.assertEquals("n01ToEdit", roleTypeDto.getName());
		
		roleTypeDto.setName("n01");
		__inject__(RoleTypeRepresentation.class).updateOne(roleTypeDto, null);
		RoleTypeDto updatedRoleTypeDto = (RoleTypeDto) __inject__(RoleTypeRepresentation.class).getOne(code, "business",null).getEntity();
		assertionHelper.assertEquals("n01ToEdit", updatedRoleTypeDto.getName());
		
		roleTypeDto.setName("n01");
		__inject__(RoleTypeRepresentation.class).updateOne(roleTypeDto, "name");
		updatedRoleTypeDto = (RoleTypeDto) __inject__(RoleTypeRepresentation.class).getOne(code, "business",null).getEntity();
		assertionHelper.assertEquals("n01", updatedRoleTypeDto.getName());
		
		//__inject__(TestRepresentationUpdate.class).addObjects(roleType).execute();
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
