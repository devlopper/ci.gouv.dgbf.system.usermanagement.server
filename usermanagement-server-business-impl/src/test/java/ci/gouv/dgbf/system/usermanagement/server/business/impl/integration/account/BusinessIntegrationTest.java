package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.map.MapHelper;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.string.Strings;
import org.cyk.utility.system.node.SystemNodeServer;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

public class BusinessIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
		super.__listenBeforeCallCountIsZero__();
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	//@Test
	public void read_systemName() throws Exception{
		System.setProperty("cyk.parameter.system.name", "Gestion des acteurs");
		assertThat(__inject__(SystemNodeServer.class).getName()).isEqualTo("Gestion des acteurs");
	}
	
	@Test
	public void create_functionCategory() throws Exception{
		__inject__(TestBusinessCreate.class).addObjects(new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomCode__())).execute();
	}
	
	@Test
	public void create_function() throws Exception{
		FunctionCategory functionCategory = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		Function function = new Function().setCode(__getRandomCode__()).setName(__getRandomCode__()).setCategory(functionCategory);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(functionCategory).addObjects(function).execute();
	}
	
	@Test
	public void create_functionScope() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		Scope scope = new Scope().setIdentifier("21").setType(scopeType);
		FunctionCategory functionCategory = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		Function function = new Function().setCode("ASSISTANT").setName("Assistant").setCategory(functionCategory);
		FunctionScope role = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionCategory,function).addObjects(role).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				assertThat(role.getCode()).isEqualTo("ASSISTANT_MINISTERE_21");
				assertThat(role.getName()).isEqualTo("Assistant ministère 21");
			}
		}).execute();
	}
	
	@Test
	public void create_functionScope_ua() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("UA").setName("Unité administrative");
		Scope scope = new Scope().setIdentifier("21").setType(scopeType);
		FunctionCategory functionCategory = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		Function function = new Function().setCode("RUA").setName("Responsable unité administrative").setCategory(functionCategory);
		FunctionScope role = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionCategory,function).addObjects(role).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				assertThat(role.getCode()).isEqualTo("RUA_UA_21");
				assertThat(role.getName()).isEqualTo("Responsable unité administrative 21");
			}
		}).execute();
	}
	
	@Test
	public void create_service() throws Exception{
		Service service = new Service().setIdentifier(__getRandomCode__());
		__inject__(TestBusinessCreate.class).addObjects(service).execute();
	}
	
	@Test
	public void create_profile() throws Exception{
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestBusinessCreate.class).addObjects(profile).execute();
	}
	
	@Test
	public void create_profile_with_functions() throws Exception{
		FunctionCategory category = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionCategoryBusiness.class).create(category);
		__inject__(FunctionBusiness.class).create(new Function().setCode("f01").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f02").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f03").setName(__getRandomName__()).setCategory(category));
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(0);
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p01").setName(__getRandomName__())
				.addFunctions(__inject__(FunctionPersistence.class).readOneByBusinessIdentifier("f01"))
				.addFunctions(__inject__(FunctionPersistence.class).readOneByBusinessIdentifier("f03"))
				);
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(2);
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p02").setName(__getRandomName__())
				.addFunctions(__inject__(FunctionPersistence.class).readOneByBusinessIdentifier("f01"))
				.addFunctions(__inject__(FunctionPersistence.class).readOneByBusinessIdentifier("f02"))
				.addFunctions(__inject__(FunctionPersistence.class).readOneByBusinessIdentifier("f03"))
				);
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(5);
		Profile profile = __inject__(ProfileBusiness.class).findOneByBusinessIdentifier("p02");
		profile.getFunctions(Boolean.TRUE).removeAll();
		profile.getFunctions(Boolean.TRUE).add(__inject__(FunctionPersistence.class).readOneByBusinessIdentifier("f02"));
		__inject__(ProfileBusiness.class).update(profile);
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(3);
		__inject__(ProfileBusiness.class).deleteByBusinessIdentifier("p01");
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(1);
		
		__inject__(ProfileBusiness.class).deleteAll();
		__inject__(FunctionBusiness.class).deleteAll();
		__inject__(FunctionCategoryBusiness.class).deleteAll();
	}
	
	@Test
	public void create_profileFunction() throws Exception{
		Function function = new Function().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setCategory(new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__()));
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileFunction profileFunction = new ProfileFunction();
		profileFunction.setProfile(profile);
		profileFunction.setFunction(function);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(function.getCategory(),function,profile).addObjects(profileFunction).execute();
	}
	
	@Test
	public void read_profileByFunctions() throws Exception{
		FunctionCategory category = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionCategoryBusiness.class).create(category);
		__inject__(FunctionBusiness.class).create(new Function().setCode("f01").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f02").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f03").setName(__getRandomName__()).setCategory(category));
		
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p01").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p02").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p03").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p04").setName(__getRandomName__()));
		
		Collection<ProfileFunction> profileFunctions = __inject__(ProfileFunctionBusiness.class).findMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isEmpty();
		
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p01").setFunctionFromCode("f01"));
		profileFunctions = __inject__(ProfileFunctionBusiness.class).findMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f03"));
		
		profileFunctions = __inject__(ProfileFunctionBusiness.class).findMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = __inject__(ProfileFunctionBusiness.class).findMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p02")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f01"));
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f03"));
		
		profileFunctions = __inject__(ProfileFunctionBusiness.class).findMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = __inject__(ProfileFunctionBusiness.class).findMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p02")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		profileFunctions = __inject__(ProfileFunctionBusiness.class).findMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p03")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01","f02","f03");
		
		profileFunctions = __inject__(ProfileFunctionBusiness.class).findMany(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p04")));
		assertThat(profileFunctions).isEmpty();
		
		__inject__(ProfileFunctionBusiness.class).deleteAll();
		__inject__(ProfileBusiness.class).deleteAll();
		__inject__(FunctionBusiness.class).deleteAll();
		
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
		
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		__inject__(ScopeTypeBusiness.class).create(scopeType);
		Scope scope = new Scope().setIdentifier("21").setType(scopeType);
		__inject__(ScopeBusiness.class).create(scope);
		FunctionCategory functionCategory = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionCategoryBusiness.class).create(functionCategory);
		Function function = new Function().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(functionCategory);
		__inject__(FunctionBusiness.class).create(function);
		FunctionScope functionScope = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(FunctionScopeBusiness.class).create(functionScope);
		Profile profile = new Profile().setCode("p001").setName(__getRandomName__());
		__inject__(ProfileBusiness.class).create(profile);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addFunctionScopes(functionScope);
		userAccount.addProfiles(profile);
		__inject__(TestBusinessCreate.class).setName("Create user account").addObjects(userAccount).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				UserAccount userAccount01 = __inject__(UserAccountBusiness.class).findOneBySystemIdentifier(userAccount.getIdentifier());
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getFunctionScopes()).isNull();
				
				userAccount01 = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_FUNCTION_SCOPES));
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getFunctionScopes()).isNotNull();
				assertThat(userAccount01.getFunctionScopes().get()).isNotEmpty();
				assertThat(userAccount01.getFunctionScopes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
				assertThat(userAccount01.getProfiles()).isNull();
				
				userAccount01 = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_PROFILES));
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getProfiles()).isNotNull();
				assertThat(userAccount01.getProfiles().get()).isNotEmpty();
				assertThat(userAccount01.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001");
				assertThat(userAccount01.getFunctionScopes()).isNull();
				
				userAccount01 = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(),new Properties().setFields(Arrays.asList(UserAccount.FIELD_FUNCTION_SCOPES,UserAccount.FIELD_PROFILES)));
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getFunctionScopes()).isNotNull();
				assertThat(userAccount01.getFunctionScopes().get()).isNotEmpty();
				assertThat(userAccount01.getFunctionScopes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
				assertThat(userAccount01.getProfiles()).isNotNull();
				assertThat(userAccount01.getProfiles().get()).isNotEmpty();
				assertThat(userAccount01.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001");
				
				UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
				UserRepresentation userRepresentation = userResource.toRepresentation();
				Map<String,List<String>> attributes = userRepresentation.getAttributes();
				assertThat(attributes).contains(
						new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
						).hasSize(1);
				
			}
		}).execute();
		
		__inject__(ProfileBusiness.class).deleteAll();
		__inject__(FunctionScopeBusiness.class).deleteAll();
		__inject__(FunctionBusiness.class).deleteAll();
		__inject__(FunctionCategoryBusiness.class).deleteAll();
		__inject__(ScopeBusiness.class).deleteAll();
		__inject__(ScopeTypeBusiness.class).deleteAll();
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}else {
			
		}
	}
	
	@Test
	public void create_userAccountProfile() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		UserAccountProfile userAccountProfile = new UserAccountProfile().setUserAccount(userAccount).setProfile(profile);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount(),userAccount,profile).addObjects(userAccountProfile).execute();
	}
	
	@Test
	public void update_userAccount() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		__inject__(ScopeTypeBusiness.class).create(scopeType);
		Scope scope = new Scope().setIdentifier("21").setType(scopeType);
		__inject__(ScopeBusiness.class).create(scope);
		FunctionCategory functionCategory = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionCategoryBusiness.class).create(functionCategory);
		Function function = new Function().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(functionCategory);
		__inject__(FunctionBusiness.class).create(function);
		FunctionScope functionScope = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(FunctionScopeBusiness.class).create(functionScope);
		function = new Function().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setCategory(functionCategory);
		__inject__(FunctionBusiness.class).create(function);
		functionScope = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(FunctionScopeBusiness.class).create(functionScope);
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p001").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p002").setName(__getRandomName__()));
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addFunctionScopes(__inject__(FunctionScopePersistence.class).readOneByBusinessIdentifier("CONTROLEUR_FINANCIER_MINISTERE_21"));
		userAccount.addProfiles(__inject__(ProfilePersistence.class).readOneByBusinessIdentifier("p001"));
		__inject__(UserAccountBusiness.class).create(userAccount);
		
		UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		UserRepresentation userRepresentation = userResource.toRepresentation();
		Map<String,List<String>> attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
				).hasSize(1);
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
				.add(UserAccount.FIELD_PROFILES,UserAccount.FIELD_FUNCTION_SCOPES)));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotNull();
		assertThat(userAccount.getFunctionScopes().get()).isNotEmpty();
		assertThat(userAccount.getFunctionScopes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(userAccount.getProfiles()).isNotNull();
		assertThat(userAccount.getProfiles().get()).isNotEmpty();
		assertThat(userAccount.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001");
		
		userAccount.addFunctionScopes(__inject__(FunctionScopePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_21"));
		userAccount.addProfiles(__inject__(ProfilePersistence.class).readOneByBusinessIdentifier("p002"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(__inject__(Strings.class).add(UserAccount.FIELD_FUNCTION_SCOPES,UserAccount.FIELD_PROFILES)));
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
				.add(UserAccount.FIELD_PROFILES,UserAccount.FIELD_FUNCTION_SCOPES)));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotNull();
		assertThat(userAccount.getFunctionScopes().get()).isNotEmpty();
		assertThat(userAccount.getFunctionScopes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21");
		assertThat(userAccount.getProfiles()).isNotNull();
		assertThat(userAccount.getProfiles().get()).isNotEmpty();
		assertThat(userAccount.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001","p002");
		
		userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		userRepresentation = userResource.toRepresentation();
		attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
				).hasSize(1);
		
		scope = new Scope().setIdentifier("18").setType(scopeType);
		__inject__(ScopeBusiness.class).create(scope);
		functionScope = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(FunctionScopeBusiness.class).create(functionScope);
		
		userAccount.addFunctionScopes(__inject__(FunctionScopePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_18"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(UserAccount.FIELD_FUNCTION_SCOPES));
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
				.add(UserAccount.FIELD_PROFILES,UserAccount.FIELD_FUNCTION_SCOPES)));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotNull();
		assertThat(userAccount.getFunctionScopes().get()).isNotEmpty();
		assertThat(userAccount.getFunctionScopes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21","ASSISTANT_SAISIE_MINISTERE_18");
		assertThat(userAccount.getProfiles()).isNotNull();
		assertThat(userAccount.getProfiles().get()).isNotEmpty();
		assertThat(userAccount.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001","p002");
		
		userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		userRepresentation = userResource.toRepresentation();
		attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21","18"))
				).hasSize(1);
		
		__inject__(UserAccountBusiness.class).delete(userAccount);
		__inject__(ProfileBusiness.class).deleteAll();
		__inject__(FunctionScopeBusiness.class).deleteAll();
		__inject__(FunctionBusiness.class).deleteAll();
		__inject__(FunctionCategoryBusiness.class).deleteAll();
		__inject__(ScopeBusiness.class).deleteAll();
		__inject__(ScopeTypeBusiness.class).deleteAll();
	}
	
	@Test
	public void create_userAccountInterim() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		
		UserAccount interim = new UserAccount();
		interim.getUser(Boolean.TRUE).setFirstName("Yao").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		interim.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		
		UserAccountInterim userAccountInterim = new UserAccountInterim();
		userAccountInterim.setUserAccount(userAccount);
		userAccountInterim.setInterim(interim);
		userAccountInterim.setFromDate(LocalDateTime.of(2000, 1, 1,0,0));
		userAccountInterim.setToDate(LocalDateTime.of(2000, 2, 1,0,0));
		
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount(),userAccount
				,interim.getUser(),interim.getAccount(),interim).addObjects(userAccountInterim).execute();
	}
	
}
