package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.persistence.query.filter.FilterDto;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeDto;

public class RepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void create_functionType() throws Exception{
		__inject__(TestRepresentationCreate.class).addObjects(new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__())).execute();
	}
	
	@Test
	public void create_function() throws Exception{
		/*
		
		*/
		/*
		FunctionTypeDto functionType = new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestRepresentationCreate.class).addObjectsToBeCreatedArray(functionType)
			.addObjects(new FunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setType(functionType)).execute();
			*/
		
		FunctionTypeDto functionType = new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeRepresentation.class).createOne(functionType);
		FunctionDto functionDto = new FunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setType(functionType);
		__inject__(FunctionRepresentation.class).createOne(functionDto);
		
	}
	
	@Test
	public void create_function_sequentially() throws Exception{
		String functionTypeCode = __getRandomCode__();
		__inject__(FunctionTypeRepresentation.class).createOne(new FunctionTypeDto().setCode(functionTypeCode).setName(__getRandomName__()));
		String functionCode = __getRandomCode__();
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode(functionCode).setName(__getRandomName__())
				.setType(new FunctionTypeDto().setCode(functionTypeCode)));
		FunctionDto function = (FunctionDto) __inject__(FunctionRepresentation.class).getOne(functionCode, "business", null).getEntity();
		assertThat(function).isNotNull();
		assertThat(function.getType()).isNotNull();
		assertThat(function.getType().getCode()).isEqualTo(functionTypeCode);
	}
	
	@Test
	public void create_scope() throws Exception{
		ScopeTypeDto scopeType = new ScopeTypeDto().setCode(__getRandomCode__()).setName(__getRandomCode__());
		ScopeDto scope = new ScopeDto().setIdentifier("21").setType(scopeType);
		FunctionTypeDto functionType = new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		FunctionDto function = new FunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__()).setType(functionType);
		__inject__(TestRepresentationCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionType,function)
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
		FunctionTypeDto functionType = new FunctionTypeDto().setCode("c01").setName(__getRandomName__());
		__inject__(FunctionTypeRepresentation.class).createOne(functionType);
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f01").setName(__getRandomName__()).setType(new FunctionTypeDto().setCode("c01")).setIsProfileCreatableOnCreate(Boolean.FALSE));
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f02").setName(__getRandomName__()).setType(new FunctionTypeDto().setCode("c01")).setIsProfileCreatableOnCreate(Boolean.FALSE));
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f03").setName(__getRandomName__()).setType(new FunctionTypeDto().setCode("c01")).setIsProfileCreatableOnCreate(Boolean.FALSE));
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(0l);
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p01").setName(__getRandomName__()).addFunctionsByCodes("f01","f03"));
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(2l);
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p02").setName(__getRandomName__()).addFunctionsByCodes("f01","f02","f03"));
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(5l);
		__inject__(ProfileRepresentation.class).updateOne(new ProfileDto().setCode("p02").setName(__getRandomName__()).addFunctionsByCodes("f02"),Profile.FIELD_FUNCTIONS);
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(3l);
		__inject__(ProfileRepresentation.class).deleteOne(new ProfileDto().setCode("p01"));
		assertThat(__inject__(ProfileFunctionRepresentation.class).count(null).getEntity()).isEqualTo(1l);
	}
	
	@Test
	public void create_profileRoleFunction() throws Exception{
		FunctionDto function = new FunctionDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setType(new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__()));
		ProfileDto profile = new ProfileDto();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileFunctionDto profileRoleFunction = new ProfileFunctionDto();
		profileRoleFunction.setProfile(profile);
		profileRoleFunction.setFunction(function);
		__inject__(TestRepresentationCreate.class).addObjectsToBeCreatedArray(function.getType(),function,profile).addObjects(profileRoleFunction).execute();
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void get_many_profileByFunctions() throws Exception{
		FunctionTypeDto functionType = new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeRepresentation.class).createOne(functionType);
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f01").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f02").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("f03").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p01").setName(__getRandomName__()));
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p02").setName(__getRandomName__()));
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p03").setName(__getRandomName__()));
		__inject__(ProfileRepresentation.class).createOne(new ProfileDto().setCode("p04").setName(__getRandomName__()));
		
		Collection<ProfileFunctionDto> profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,null
				,__inject__(FilterDto.class).useKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE, Arrays.asList("p01"))).getEntity();
		assertThat(profileFunctions).isNull();
		
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunctionDto().setProfile(new ProfileDto().setCode("p01")).setFunction(new FunctionDto().setCode("f01")));
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,ProfileFunction.FIELD_FUNCTION
				,__inject__(FilterDto.class).useKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE, Arrays.asList("p01"))).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");

		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,ProfileFunction.FIELD_PROFILE
				,__inject__(FilterDto.class).useKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_FUNCTION, Arrays.asList("f01"))).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p01");
		
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunctionDto().setProfile(new ProfileDto().setCode("p02")).setFunction(new FunctionDto().setCode("f02")));
		__inject__(ProfileFunctionRepresentation.class).createOne(new ProfileFunctionDto().setProfile(new ProfileDto().setCode("p02")).setFunction(new FunctionDto().setCode("f03")));
		
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,ProfileFunction.FIELD_FUNCTION
				,__inject__(FilterDto.class).useKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE, Arrays.asList("p01"))).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,ProfileFunction.FIELD_FUNCTION
				,__inject__(FilterDto.class).useKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE, Arrays.asList("p02"))).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,ProfileFunction.FIELD_PROFILE
				,__inject__(FilterDto.class).useKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_FUNCTION, Arrays.asList("f01"))).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p01");
		
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,ProfileFunction.FIELD_PROFILE
				,__inject__(FilterDto.class).useKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_FUNCTION, Arrays.asList("f02"))).getEntity();
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p02");
		
		profileFunctions = (Collection<ProfileFunctionDto>) __inject__(ProfileFunctionRepresentation.class).getMany(Boolean.FALSE,null,null,ProfileFunction.FIELD_PROFILE
				,__inject__(FilterDto.class).useKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_FUNCTION, Arrays.asList("f03"))).getEntity();
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
	}
	
	@Test
	public void create_userAccount(){
		ScopeTypeDto scopeType = new ScopeTypeDto().setCode("MINISTERE").setName("Ministère");
		__inject__(ScopeTypeRepresentation.class).createOne(scopeType);
		ScopeDto scope = new ScopeDto().setIdentifier("21").setType(scopeType);
		__inject__(ScopeRepresentation.class).createOne(scope);
		FunctionTypeDto functionType = new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeRepresentation.class).createOne(functionType);
		FunctionDto function = new FunctionDto().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setType(functionType);
		__inject__(FunctionRepresentation.class).createOne(function);
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("CE").setName(__getRandomName__()).setType(functionType));
		FunctionScopeDto functionScope = new FunctionScopeDto().setFunction(function).setScope(scope);
		__inject__(FunctionScopeRepresentation.class).createOne(functionScope);
		ProfileDto profile = new ProfileDto().setCode("p001").setName(__getRandomName__());
		__inject__(ProfileRepresentation.class).createOne(profile);
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.addFunctionsByCodes("ASSISTANT_SAISIE");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addFunctionScopes(functionScope).addProfiles(profile);
		__inject__(UserAccountRepresentation.class).createOne(userAccount);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null, null).getEntity();
		assertThat(userAccount).as("user account is null").isNotNull();
		assertThat(userAccount.getFunctionScopes()).as("user account roles collection is not null").isNull();
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(),null,UserAccount.FIELD_PROFILES+","+UserAccount.FIELD_FUNCTION_SCOPES+",user,identifier").getEntity();
		assertThat(userAccount).as("user account is null").isNotNull();
		assertThat(userAccount.getFunctionScopes()).as("user account roles collection is null").isNotNull();
		assertThat(userAccount.getFunctionScopes()).as("user account roles collection is empty").isNotEmpty();
		assertThat(userAccount.getFunctionScopes().stream().map(FunctionScopeDto::getCode).collect(Collectors.toList())).contains("ASSISTANT_SAISIE_MINISTERE_21");
		assertThat(userAccount.getProfiles()).as("user account profiles collection is null").isNotNull();
		assertThat(userAccount.getProfiles()).as("user account profiles collection is empty").isNotEmpty();
		assertThat(userAccount.getProfiles().stream().map(ProfileDto::getCode).collect(Collectors.toList())).contains("p001");
		assertThat(userAccount.getUser()).isNotNull();
		assertThat(userAccount.getUser().getFunctions()).isNull();
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(),"system",UserAccount.FIELD_PROFILES+","+UserAccount.FIELD_FUNCTION_SCOPES+",functions,identifier,user").getEntity();
		assertThat(userAccount).as("user account is null").isNotNull();
		assertThat(userAccount.getFunctionScopes()).as("user account roles collection is null").isNotNull();
		assertThat(userAccount.getFunctionScopes()).as("user account roles collection is empty").isNotEmpty();
		assertThat(userAccount.getFunctionScopes().stream().map(FunctionScopeDto::getCode).collect(Collectors.toList())).contains("ASSISTANT_SAISIE_MINISTERE_21");
		assertThat(userAccount.getProfiles()).as("user account profiles collection is null").isNotNull();
		assertThat(userAccount.getProfiles()).as("user account profiles collection is empty").isNotEmpty();
		assertThat(userAccount.getProfiles().stream().map(ProfileDto::getCode).collect(Collectors.toList())).contains("p001");
		assertThat(userAccount.getUser()).isNotNull();
		assertThat(userAccount.getUser().getFunctions()).isNotNull();
		assertThat(userAccount.getUser().getFunctions()).isNotNull();
		assertThat(userAccount.getUser().getFunctions()).isNotEmpty();
		assertThat(userAccount.getUser().getFunctions().stream().map(FunctionDto::getCode).collect(Collectors.toList())).containsOnly("ASSISTANT_SAISIE");
		
		userAccount.addFunctionsByCodes("CE");
		assertThat(userAccount.getUser().getFunctions().stream().map(FunctionDto::getCode).collect(Collectors.toList())).containsOnly("ASSISTANT_SAISIE","CE");
		assertThat(__inject__(UserFunctionRepresentation.class).count(null).getEntity()).isEqualTo(1l);
		__inject__(UserAccountRepresentation.class).updateOne(userAccount, "user.functions");
		assertThat(__inject__(UserFunctionRepresentation.class).count(null).getEntity()).isEqualTo(2l);
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(),null,UserAccount.FIELD_PROFILES+","+UserAccount.FIELD_FUNCTION_SCOPES+",functions,user,identifier").getEntity();
		assertThat(userAccount.getUser()).isNotNull();
		assertThat(userAccount.getUser().getFunctions()).isNotNull();
		assertThat(userAccount.getUser().getFunctions()).isNotNull();
		assertThat(userAccount.getUser().getFunctions()).isNotEmpty();
		assertThat(userAccount.getUser().getFunctions().stream().map(FunctionDto::getCode).collect(Collectors.toList())).containsOnly("ASSISTANT_SAISIE","CE");
		
	}
	
	//@Test
	public void updateProfile(){
		FunctionTypeDto functionType = new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeRepresentation.class).createOne(functionType);
		FunctionDto function = new FunctionDto().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setType(functionType);
		__inject__(FunctionRepresentation.class).createOne(function);
		__inject__(FunctionRepresentation.class).createOne(new FunctionDto().setCode("CE").setName(__getRandomName__()).setType(functionType));
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.addFunctionsByCodes("ASSISTANT_SAISIE");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		__inject__(UserAccountRepresentation.class).createOne(userAccount);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null, "identifier,user.identifier").getEntity();
		assertThat(userAccount).as("user account is null").isNotNull();
		assertThat(userAccount.getUser()).as("user is not null").isNotNull();
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
	}
	
	@Test
	public void update_userAccount() throws Exception{
		ScopeTypeDto scopeType = new ScopeTypeDto().setCode("MINISTERE").setName("Ministère");
		__inject__(ScopeTypeRepresentation.class).createOne(scopeType);
		ScopeDto scope = new ScopeDto().setIdentifier("21").setType(scopeType);
		__inject__(ScopeRepresentation.class).createOne(scope);
		FunctionTypeDto functionType = new FunctionTypeDto().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeRepresentation.class).createOne(functionType);
		FunctionDto function = new FunctionDto().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setType(functionType);
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
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,"identifier,"+UserAccount.FIELD_PROFILES+","+UserAccount.FIELD_FUNCTION_SCOPES+",identifier").getEntity();
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotEmpty();
		assertThat(userAccount.getFunctionScopes().stream().map(FunctionScopeDto::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(userAccount.getProfiles()).as("user account profiles collection is null").isNotNull();
		assertThat(userAccount.getProfiles()).as("user account profiles collection is empty").isNotEmpty();
		assertThat(userAccount.getProfiles().stream().map(ProfileDto::getCode).collect(Collectors.toList())).contains("p001");
		
		function = new FunctionDto().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setType(functionType);
		__inject__(FunctionRepresentation.class).createOne(function);
		functionScope = new FunctionScopeDto().setFunction(function).setScope(scope);
		__inject__(FunctionScopeRepresentation.class).createOne(functionScope);
		profile = new ProfileDto().setCode("p002").setName(__getRandomName__());
		__inject__(ProfileRepresentation.class).createOne(profile);
		
		userAccount.addFunctionScopes(functionScope).addProfiles(profile);
		__inject__(UserAccountRepresentation.class).updateOne(userAccount,UserAccountDto.FIELD_PROFILES+","+UserAccountDto.FIELD_FUNCTION_SCOPES);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,"identifier,"+UserAccountDto.FIELD_PROFILES+","+UserAccount.FIELD_FUNCTION_SCOPES).getEntity();
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotEmpty();
		assertThat(userAccount.getFunctionScopes().stream().map(FunctionScopeDto::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21");
		assertThat(userAccount.getProfiles()).as("user account profiles collection is null").isNotNull();
		assertThat(userAccount.getProfiles()).as("user account profiles collection is empty").isNotEmpty();
		assertThat(userAccount.getProfiles().stream().map(ProfileDto::getCode).collect(Collectors.toList())).contains("p001","p002");
		
		assertThat(userAccount.getScopes()).isNull();
		userAccount.addScopesByIdentifiers("21");
		__inject__(UserAccountRepresentation.class).updateOne(userAccount, "scopes");
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,"identifier,scopes").getEntity();
		assertThat(userAccount.getScopes()).isNotNull();
		assertThat(userAccount.getScopes()).hasSize(1);
		assertThat(userAccount.getScopes().stream().map(ScopeDto::getIdentifier).collect(Collectors.toList())).contains("21");
	}
	
	//@Test
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
	
}
