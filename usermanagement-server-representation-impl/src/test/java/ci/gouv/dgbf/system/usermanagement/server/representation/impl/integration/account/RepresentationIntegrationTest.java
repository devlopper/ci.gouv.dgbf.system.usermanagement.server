package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.PosteLocationRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.PosteLocationTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RoleCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

public class RepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
		super.__listenBeforeCallCountIsZero__();
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Test
	public void create_roleCategory() throws Exception{
		__inject__(TestRepresentationCreate.class).addObjects(new RoleCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__())).execute();
	}
	
	@Test
	public void create_roleFunction() throws Exception{
		RoleCategoryDto category = new RoleCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestRepresentationCreate.class).addObjectsToBeCreatedArray(category)
			.addObjects(new RoleFunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setCategory(category)).execute();
	}
	
	@Test
	public void create_rolePoste() throws Exception{
		PosteLocationTypeDto locationType = new PosteLocationTypeDto().setCode(__getRandomCode__()).setName(__getRandomCode__());
		PosteLocationDto location = new PosteLocationDto().setIdentifier("21").setType(locationType);
		RoleCategoryDto category = new RoleCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		RoleFunctionDto function = new RoleFunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setCategory(category);
		__inject__(TestRepresentationCreate.class).addObjectsToBeCreatedArray(locationType,location,category,function)
			.addObjects(new RolePosteDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setFunction(function).setLocation(location)).execute();
	}
	
	@Test
	public void create_userAccount() throws Exception{
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			startServersZookeeperAndKafka();
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		PosteLocationTypeDto locationType = new PosteLocationTypeDto().setCode("MINISTERE").setName("Ministère");
		PosteLocationDto location = new PosteLocationDto().setIdentifier("21").setType(locationType);
		RoleCategoryDto category = new RoleCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		RoleFunctionDto function = new RoleFunctionDto().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setCategory(category);
		RolePosteDto poste = new RolePosteDto().setFunction(function).setLocation(location);
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostes(poste);
		__inject__(TestRepresentationCreate.class).setName("Create user account").addObjectsToBeCreatedArray(locationType,location,category,function,poste).addObjects(userAccount).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				UserAccountDto userAccount01 = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null, null).getEntity();
				assertThat(userAccount01).as("user account is null").isNotNull();
				assertThat(userAccount01.getRolePostes()).as("user account roles collection is not null").isNull();
				
				userAccount01 = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(),null,UserAccount.FIELD_ROLE_POSTES).getEntity();
				assertThat(userAccount01).as("user account is null").isNotNull();
				assertThat(userAccount01.getRolePostes()).as("user account roles collection is null").isNotNull();
				assertThat(userAccount01.getRolePostes().getCollection()).as("user account roles collection is empty").isNotEmpty();
				assertThat(userAccount01.getRolePostes().getCollection().stream().map(RolePosteDto::getCode).collect(Collectors.toList())).contains("ASSISTANT_SAISIE_MINISTERE_21");
			}
		}).execute();
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}
	}
	
	@Test
	public void update_userAccount() throws Exception{
		PosteLocationTypeDto locationType = new PosteLocationTypeDto().setCode("MINISTERE").setName("Ministère");
		__inject__(PosteLocationTypeRepresentation.class).createOne(locationType);
		PosteLocationDto location = new PosteLocationDto().setIdentifier("21").setType(locationType);
		__inject__(PosteLocationRepresentation.class).createOne(location);
		RoleCategoryDto category = new RoleCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(RoleCategoryRepresentation.class).createOne(category);
		RoleFunctionDto function = new RoleFunctionDto().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(category);
		__inject__(RoleFunctionRepresentation.class).createOne(function);
		RolePosteDto poste = new RolePosteDto().setFunction(function).setLocation(location);
		__inject__(RolePosteRepresentation.class).createOne(poste);
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostes(poste);
		
		__inject__(UserAccountRepresentation.class).createOne(userAccount);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,UserAccount.FIELD_ROLE_POSTES).getEntity();
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getRolePostes()).isNotNull();
		assertThat(userAccount.getRolePostes().getCollection()).isNotEmpty();
		assertThat(userAccount.getRolePostes().getCollection().stream().map(RolePosteDto::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		
		function = new RoleFunctionDto().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setCategory(category);
		__inject__(RoleFunctionRepresentation.class).createOne(function);
		poste = new RolePosteDto().setFunction(function).setLocation(location);
		__inject__(RolePosteRepresentation.class).createOne(poste);
		
		userAccount.addRolePostes(poste);
		__inject__(UserAccountRepresentation.class).updateOne(userAccount,UserAccountDto.FIELD_ROLE_POSTES);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,UserAccount.FIELD_ROLE_POSTES).getEntity();
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getRolePostes()).isNotNull();
		assertThat(userAccount.getRolePostes().getCollection()).isNotEmpty();
		assertThat(userAccount.getRolePostes().getCollection().stream().map(RolePosteDto::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21");
		
		__inject__(UserAccountRepresentation.class).deleteOne(userAccount);
		__inject__(RolePosteRepresentation.class).deleteAll();
		__inject__(RoleFunctionRepresentation.class).deleteAll();
		__inject__(RolePosteRepresentation.class).deleteAll();
		__inject__(PosteLocationRepresentation.class).deleteAll();
		__inject__(PosteLocationTypeRepresentation.class).deleteAll();
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
