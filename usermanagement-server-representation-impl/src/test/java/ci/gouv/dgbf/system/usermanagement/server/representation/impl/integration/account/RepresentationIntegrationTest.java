package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.ServiceRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
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
		
		String code = __getRandomCode__();
		RoleDto role = new RoleDto().setCode(code).setName(__getRandomCode__());
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
	public void read_service() throws Exception{
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		@SuppressWarnings("unchecked")
		Collection<ServiceDto> roleDtos = (Collection<ServiceDto>) __inject__(ServiceRepresentation.class).getMany(null, null, null,null).getEntity();
		assertThat(roleDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("WORKFLOW");
	}
	
	@Test
	public void create_userAccount() throws Exception{
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			startServersZookeeperAndKafka();
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress("kycdev@gmail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRoles("CE");
		__inject__(TestRepresentationCreate.class).addObjects(userAccount).setIsCatchThrowable(Boolean.FALSE).execute();
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
