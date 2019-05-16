package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.ServiceRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

public class RepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void read_roleCategory() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		@SuppressWarnings("unchecked")
		Collection<RoleCategoryDto> roleCategoryDtos = (Collection<RoleCategoryDto>) __inject__(RoleCategoryRepresentation.class).getMany(null, null, null,null).getEntity();
		assertThat(roleCategoryDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
	
	@Test
	public void read_roleFunction() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		@SuppressWarnings("unchecked")
		Collection<RoleFunctionDto> roleFunctionDtos = (Collection<RoleFunctionDto>) __inject__(RoleFunctionRepresentation.class).getMany(null, null, null,null).getEntity();
		assertThat(roleFunctionDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("RP","DIRECTEUR");
	}
	
	@Test
	public void read_rolePoste() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		@SuppressWarnings("unchecked")
		Collection<RolePosteDto> rolePosteDtos = (Collection<RolePosteDto>) __inject__(RolePosteRepresentation.class).getMany(null, null, null,null).getEntity();
		assertThat(rolePosteDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("AS_MIN_21","CF_MIN_21");
	}
	
	@Test
	public void create_role() throws Exception{
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
	public void read_role() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		@SuppressWarnings("unchecked")
		Collection<RoleDto> roleDtos = (Collection<RoleDto>) __inject__(RoleRepresentation.class).getMany(null, null, null,null).getEntity();
		assertThat(roleDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE","AS","RBOP","DIRECTEUR");
	}
	
	@Test
	public void create_roleType() throws Exception{
		RoleTypeDto roleType = new RoleTypeDto().setCode(__getRandomCode__()).setName(__getRandomCode__());
		__inject__(TestRepresentationCreate.class).addObjects(roleType).execute();
	}
	
	@Test
	public void update_roleType() throws Exception{
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
	
	@Test
	public void read_service() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		@SuppressWarnings("unchecked")
		Collection<ServiceDto> roleDtos = (Collection<ServiceDto>) __inject__(ServiceRepresentation.class).getMany(null, null, null,null).getEntity();
		assertThat(roleDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("WORKFLOW");
	}
	
	@Test
	public void create_userAccount() throws Exception{
		/*UserDto user = new UserDto().setElectronicMailAddress("kycdev@gmail.com").setPerson(new UserNaturalPersonDto().setFirstName("Zadi").setLastNames("Representation"));
		UserAccountDto userAccount = new UserAccountDto().setCode("ua01").setUser(user);
		__inject__(TestRepresentationCreate.class).addObjects(userAccount).execute();
		*/
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
