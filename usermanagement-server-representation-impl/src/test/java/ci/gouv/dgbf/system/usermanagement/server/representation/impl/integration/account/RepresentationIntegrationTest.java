package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.map.MapHelper;
import org.cyk.utility.object.ObjectToStringBuilder;
import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeDto;
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
		__inject__(TestRepresentationCreate.class).addObjects(new FunctionCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__())).execute();
	}
	
	@Test
	public void create_roleFunction() throws Exception{
		FunctionCategoryDto category = new FunctionCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestRepresentationCreate.class).addObjectsToBeCreatedArray(category)
			.addObjects(new FunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setCategory(category)).execute();
	}
	
	@Test
	public void create_scope() throws Exception{
		ScopeTypeDto scopeType = new ScopeTypeDto().setCode(__getRandomCode__()).setName(__getRandomCode__());
		ScopeDto scope = new ScopeDto().setIdentifier("21").setType(scopeType);
		FunctionCategoryDto category = new FunctionCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		FunctionDto function = new FunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setCategory(category);
		__inject__(TestRepresentationCreate.class).addObjectsToBeCreatedArray(scopeType,scope,category,function)
			.addObjects(new FunctionScopeDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setFunction(function).setScope(scope)).execute();
	}
	
	@Test
	public void create_profile() throws Exception{
		ProfileDto profile = new ProfileDto();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestRepresentationCreate.class).addObjects(profile).execute();
	}
	
	@Test
	public void create_profile_withFunctions() throws Exception{
		FunctionCategoryDto category = new FunctionCategoryDto().setCode("c01").setName(__getRandomName__());
		__inject__(FunctionCategoryRepresentation.class).createOne(category);
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f01").setName(__getRandomName__()).setCategory(new FunctionCategoryDto().setCode("c01")));
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f02").setName(__getRandomName__()).setCategory(new FunctionCategoryDto().setCode("c01")));
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f03").setName(__getRandomName__()).setCategory(new FunctionCategoryDto().setCode("c01")));
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(0l);
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p01").setName(__getRandomName__()).addFunctionsByCodes("f01","f03"));
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(2l);
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p02").setName(__getRandomName__()).addFunctionsByCodes("f01","f02","f03"));
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(5l);
		__inject__(ProfileRepresentation.class).updateOne(new ProfileDto().setCode("p02").setName(__getRandomName__()).addFunctionsByCodes("f02"),Profile.FIELD_FUNCTIONS);
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(3l);
		__inject__(ProfileRepresentation.class).deleteOne(new ProfileDto().setCode("p01"));
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(1l);
		
		__inject__(ProfileRepresentation.class).deleteAll();
		__inject__(FunctionRepresentation.class).deleteAll();
		__inject__(FunctionCategoryRepresentation.class).deleteAll();
	}
	
	@Test
	public void create_profileRoleFunction() throws Exception{
		FunctionDto function = new FunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setCategory(new FunctionCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__()));
		ProfileDto profile = new ProfileDto();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileFunctionDto profileRoleFunction = new ProfileFunctionDto();
		profileRoleFunction.setProfile(profile);
		profileRoleFunction.setFunction(function);
		__inject__(TestRepresentationCreate.class).addObjectsToBeCreatedArray(function.getCategory(),function,profile).addObjects(profileRoleFunction).execute();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void get_many_profileByFunctions() throws Exception{
		FunctionCategoryDto category = new FunctionCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionCategoryRepresentation.class).createOne(category);
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f01").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f02").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f03").setName(__getRandomName__()).setCategory(category));
		
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p01").setName(__getRandomName__()));
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p02").setName(__getRandomName__()));
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p03").setName(__getRandomName__()));
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p04").setName(__getRandomName__()));
		
		Collection<ProfileFunctionDto> profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(ObjectToStringBuilder.class).setObject(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")).execute().getOutput()).getEntity();
		assertThat(profileFunctions).isNull();
		
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunctionDto().setProfile(new ProfileDto().setCode("p01")).setFunction(new FunctionDto().setCode("f01")));
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(ObjectToStringBuilder.class).setObject(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")).execute().getOutput()).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");

		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(ObjectToStringBuilder.class).setObject(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "f01")).execute().getOutput()).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p01");
		
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunctionDto().setProfile(new ProfileDto().setCode("p02")).setFunction(new FunctionDto().setCode("f02")));
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunctionDto().setProfile(new ProfileDto().setCode("p02")).setFunction(new FunctionDto().setCode("f03")));
		
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(ObjectToStringBuilder.class).setObject(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")).execute().getOutput()).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(ObjectToStringBuilder.class).setObject(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p02")).execute().getOutput()).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(ObjectToStringBuilder.class).setObject(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "f01")).execute().getOutput()).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p01");
		
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(ObjectToStringBuilder.class).setObject(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "f02")).execute().getOutput()).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p02");
		
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(ObjectToStringBuilder.class).setObject(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "f03")).execute().getOutput()).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p02");
		
		/*
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f01"));
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f03"));
		
		profileFunctions = __inject__(ProfileFunctionRepresentation.class).getMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = __inject__(ProfileFunctionRepresentation.class).getMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p02")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		profileFunctions = __inject__(ProfileFunctionRepresentation.class).getMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p03")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01","f02","f03");
		
		profileFunctions = __inject__(ProfileFunctionRepresentation.class).getMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p04")));
		assertThat(profileFunctions).isEmpty();
		*/
		__inject__(ProfileFunctionRepresentation.class).deleteAll();
		__inject__(ProfileRepresentation.class).deleteAll();
		__inject__(FunctionRepresentation.class).deleteAll();
		
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
		
		ScopeTypeDto scopeType = new ScopeTypeDto().setCode("MINISTERE").setName("Ministère");
		ScopeDto scope = new ScopeDto().setIdentifier("21").setType(scopeType);
		FunctionCategoryDto category = new FunctionCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		FunctionDto function = new FunctionDto().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setCategory(category);
		FunctionScopeDto functionScope = new FunctionScopeDto().setFunction(function).setScope(scope);
		ProfileDto profile = new ProfileDto().setCode("p001").setName(__getRandomName__());
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addFunctionScopes(functionScope).addProfiles(profile);
		__inject__(TestRepresentationCreate.class).setName("Create user account").addObjectsToBeCreatedArray(scopeType,scope,category,function,functionScope,profile).addObjects(userAccount).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				UserAccountDto userAccount01 = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null, null).getEntity();
				assertThat(userAccount01).as("user account is null").isNotNull();
				assertThat(userAccount01.getFunctionScopes()).as("user account roles collection is not null").isNull();
				
				userAccount01 = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(),null,UserAccount.FIELD_PROFILES+","+UserAccount.FIELD_FUNCTION_SCOPES).getEntity();
				assertThat(userAccount01).as("user account is null").isNotNull();
				assertThat(userAccount01.getFunctionScopes()).as("user account roles collection is null").isNotNull();
				assertThat(userAccount01.getFunctionScopes().getCollection()).as("user account roles collection is empty").isNotEmpty();
				assertThat(userAccount01.getFunctionScopes().getCollection().stream().map(FunctionScopeDto::getCode).collect(Collectors.toList())).contains("ASSISTANT_SAISIE_MINISTERE_21");
				assertThat(userAccount01.getProfiles()).as("user account profiles collection is null").isNotNull();
				assertThat(userAccount01.getProfiles().getCollection()).as("user account profiles collection is empty").isNotEmpty();
				assertThat(userAccount01.getProfiles().getCollection().stream().map(ProfileDto::getCode).collect(Collectors.toList())).contains("p001");
			}
		}).execute();
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}
	}
	
	@Test
	public void create_userAccountProfile() throws Exception{
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		__inject__(UserAccountRepresentation.class).createOne(userAccount);
		
		ProfileDto profile = new ProfileDto();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(ProfileRepresentation.class).createOne(profile);
		
		UserAccountProfileDto userAccountProfile = new UserAccountProfileDto().setUserAccount(userAccount).setProfile(profile);
		__inject__(TestRepresentationCreate.class).addObjects(userAccountProfile).execute();
		
		__inject__(UserAccountRepresentation.class).deleteAll();
		__inject__(ProfileRepresentation.class).deleteAll();
	}
	
	@Test
	public void update_userAccount() throws Exception{
		ScopeTypeDto scopeType = new ScopeTypeDto().setCode("MINISTERE").setName("Ministère");
		__inject__(ScopeTypeRepresentation.class).createOne(scopeType);
		ScopeDto scope = new ScopeDto().setIdentifier("21").setType(scopeType);
		__inject__(ScopeRepresentation.class).createOne(scope);
		FunctionCategoryDto category = new FunctionCategoryDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionCategoryRepresentation.class).createOne(category);
		FunctionDto function = new FunctionDto().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(category);
		__inject__(FunctionRepresentation.class).createOne(function);
		FunctionScopeDto functionScope = new FunctionScopeDto().setFunction(function).setScope(scope);
		__inject__(FunctionScopeRepresentation.class).createOne(functionScope);
		ProfileDto profile = new ProfileDto().setCode("p001").setName(__getRandomName__());
		__inject__(ProfileRepresentation.class).createOne(profile);
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addFunctionScopes(functionScope).addProfiles(profile);
		
		__inject__(UserAccountRepresentation.class).createOne(userAccount);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,UserAccount.FIELD_PROFILES+","+UserAccount.FIELD_FUNCTION_SCOPES).getEntity();
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotNull();
		assertThat(userAccount.getFunctionScopes().getCollection()).isNotEmpty();
		assertThat(userAccount.getFunctionScopes().getCollection().stream().map(FunctionScopeDto::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(userAccount.getProfiles()).as("user account profiles collection is null").isNotNull();
		assertThat(userAccount.getProfiles().getCollection()).as("user account profiles collection is empty").isNotEmpty();
		assertThat(userAccount.getProfiles().getCollection().stream().map(ProfileDto::getCode).collect(Collectors.toList())).contains("p001");
		
		function = new FunctionDto().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setCategory(category);
		__inject__(FunctionRepresentation.class).createOne(function);
		functionScope = new FunctionScopeDto().setFunction(function).setScope(scope);
		__inject__(FunctionScopeRepresentation.class).createOne(functionScope);
		profile = new ProfileDto().setCode("p002").setName(__getRandomName__());
		__inject__(ProfileRepresentation.class).createOne(profile);
		
		userAccount.addFunctionScopes(functionScope).addProfiles(profile);
		__inject__(UserAccountRepresentation.class).updateOne(userAccount,UserAccountDto.FIELD_PROFILES+","+UserAccountDto.FIELD_FUNCTION_SCOPES);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,UserAccountDto.FIELD_PROFILES+","+UserAccount.FIELD_FUNCTION_SCOPES).getEntity();
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotNull();
		assertThat(userAccount.getFunctionScopes().getCollection()).isNotEmpty();
		assertThat(userAccount.getFunctionScopes().getCollection().stream().map(FunctionScopeDto::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21");
		assertThat(userAccount.getProfiles()).as("user account profiles collection is null").isNotNull();
		assertThat(userAccount.getProfiles().getCollection()).as("user account profiles collection is empty").isNotEmpty();
		assertThat(userAccount.getProfiles().getCollection().stream().map(ProfileDto::getCode).collect(Collectors.toList())).contains("p001","p002");
		
		__inject__(UserAccountRepresentation.class).deleteOne(userAccount);
		__inject__(FunctionScopeRepresentation.class).deleteAll();
		__inject__(FunctionRepresentation.class).deleteAll();
		__inject__(FunctionScopeRepresentation.class).deleteAll();
		__inject__(ScopeRepresentation.class).deleteAll();
		__inject__(ScopeTypeRepresentation.class).deleteAll();
	}
	
	@Test
	public void create_userAccountInterim() throws Exception{
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		
		UserAccountDto interim = new UserAccountDto();
		interim.getUser(Boolean.TRUE).setFirstName("Yao").setLastNames("Charles").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		interim.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		
		UserAccountInterimDto userAccountInterim = new UserAccountInterimDto();
		userAccountInterim.setUserAccount(userAccount);
		userAccountInterim.setInterim(interim);
		userAccountInterim.setFromDate(LocalDateTime.of(2000, 1, 1, 0, 0));
		userAccountInterim.setToDate(LocalDateTime.of(2000, 2, 1, 0, 0));
		
		__inject__(TestRepresentationCreate.class).setName("Create user account interim").addObjectsToBeCreatedArray(userAccount,interim).addObjects(userAccountInterim).execute();
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
