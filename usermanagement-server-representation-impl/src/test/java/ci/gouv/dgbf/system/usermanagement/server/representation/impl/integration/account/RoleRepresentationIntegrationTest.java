package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.cyk.utility.value.ValueUsageType;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

public class RoleRepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void createOneRole() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		String typeCode = __getRandomCode__();
		RoleType type = new RoleType().setCode(typeCode).setName(__getRandomCode__());
		__inject__(RoleTypeBusiness.class).create(type);
		
		String code = __getRandomCode__();
		RoleDto role = new RoleDto().setCode(code).setName(__getRandomCode__()).setType(new RoleTypeDto().setCode(typeCode));
		__inject__(TestRepresentationCreate.class).addObjects(role).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				RoleDto role = (RoleDto) __inject__(RoleRepresentation.class).getOne(code,"business", null).getEntity();
				assertionHelper.assertEquals(code, role.getCode());
				assertionHelper.assertFalse("role has not an identifier",role.getIdentifier().isEmpty());
			}
		}).execute();
	}
	
	@Test
	public void read() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RoleDto> roleDtos = (Collection<RoleDto>) __inject__(RoleRepresentation.class).getMany(null, null, null).getEntity();
		assertThat(roleDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE","AS","RBOP","DIRECTEUR");
	}
	
	//@Test
	public void updateOneRole() throws Exception{
		String typeCode01 = __getRandomCode__();
		String typeCode02 = __getRandomCode__();
		__inject__(RoleTypeBusiness.class).create(new RoleType().setCode(typeCode01).setName(__getRandomCode__()));
		__inject__(RoleTypeBusiness.class).create(new RoleType().setCode(typeCode02).setName(__getRandomCode__()));
		
		String roleCode01 = __getRandomCode__();
		__inject__(RoleBusiness.class).create(new Role().setCode(roleCode01).setName("n01ToEdit").setType(__inject__(RoleTypePersistence.class).readOneByBusinessIdentifier(typeCode01)));
		
		RoleDto roleDto = (RoleDto) __inject__(RoleRepresentation.class).getOne(roleCode01, ValueUsageType.BUSINESS.name(),null).getEntity();
		assertionHelper.assertEquals("n01ToEdit", roleDto.getName());
		assertionHelper.assertEquals(typeCode01, roleDto.getType());
		
		roleDto.setName("n01");
		//roleDto.setType(typeCode02);
		__inject__(RoleRepresentation.class).updateOne(roleDto, "name,type");
		RoleDto updatedRoleDto = (RoleDto) __inject__(RoleRepresentation.class).getOne(roleCode01, ValueUsageType.BUSINESS.name(),null).getEntity();
		assertionHelper.assertEquals("n01", updatedRoleDto.getName());
		assertionHelper.assertEquals(typeCode02, updatedRoleDto.getType());
		
		Role role = __inject__(RoleBusiness.class).findOneByBusinessIdentifier(roleCode01);
		assertionHelper.assertNotNull(role);
		assertionHelper.assertNotNull(role.getType());
		assertionHelper.assertEquals("n01", role.getName());
		assertionHelper.assertEquals(typeCode02, role.getType().getCode());
		
		//__inject__(TestRepresentationUpdate.class).addObjects(roleType).execute();
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
