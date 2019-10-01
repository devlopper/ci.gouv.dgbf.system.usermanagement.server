package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.server.persistence.query.filter.Filter;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.system.node.SystemNodeServer;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileServiceResource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ServiceResource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

public class BusinessIntegrationTestKeycloak extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		__inject__(ProfileTypeBusiness.class).createMany(CollectionHelper.listOf(new ProfileType().setCode(ProfileType.CODE_SYSTEM).setName("Système")
				,new ProfileType().setCode(ProfileType.CODE_UTILISATEUR).setName("Utilisateur")));
	}
	
	//@Test
	public void read_systemName() throws Exception{
		System.setProperty("cyk.parameter.system.name", "Gestion des acteurs");
		assertThat(__inject__(SystemNodeServer.class).getName()).isEqualTo("Gestion des acteurs");
	}
	
	@Test
	public void create_functionType() throws Exception{
		__inject__(TestBusinessCreate.class).addObjects(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomCode__())).execute();
	}
	
	@Test
	public void create_function() throws Exception{
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Function function = new Function().setCode(__getRandomCode__()).setName(__getRandomCode__()).setType(functionType);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(functionType).addObjects(function).execute();
	}
	
	@Test
	public void create_functionScope() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		Scope scope = new Scope().setIdentifier("21").setType(scopeType);
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Function function = new Function().setCode("ASSISTANT").setName("Assistant").setType(functionType);
		FunctionScope role = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionType,function).addObjects(role).addTryEndRunnables(new Runnable() {
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
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Function function = new Function().setCode("RUA").setName("Responsable unité administrative").setType(functionType);
		FunctionScope role = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionType,function).addObjects(role).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				assertThat(role.getCode()).isEqualTo("RUA_UA_21");
				assertThat(role.getName()).isEqualTo("Responsable unité administrative 21");
			}
		}).execute();
	}
	
	@Test
	public void create_service() throws Exception{
		__inject__(TestBusinessCreate.class).addObjects(new Service().setUrl("url")).execute();
	}
	
	@Test
	public void create_resource() throws Exception{
		__inject__(TestBusinessCreate.class).addObjects(new Resource().setCode(__getRandomCode__()).setName(__getRandomName__()).setUrl("url")).execute();
	}
	
	@Test
	public void create_serviceResource() throws Exception{
		ServiceResource serviceResource = new ServiceResource();
		serviceResource.setService(new Service().setUrl("url"));
		serviceResource.setResource(new Resource().setCode(__getRandomCode__()).setName(__getRandomName__()).setUrl("url"));
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(serviceResource.getService(),serviceResource.getResource()).addObjects(serviceResource).execute();
	}
	
	@Test
	public void create_profileServiceResource() throws Exception{
		ProfileServiceResource profileServiceResource = new ProfileServiceResource();
		profileServiceResource.setProfile(new Profile().setCode(__getRandomCode__()).setName(__getRandomName__()));
		profileServiceResource.setService(new Service().setUrl("url"));
		profileServiceResource.setResource(new Resource().setCode(__getRandomCode__()).setName(__getRandomName__()).setUrl("url"));
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(profileServiceResource.getProfile(),profileServiceResource.getService()
				,profileServiceResource.getResource()).addObjects(profileServiceResource).execute();
	}
	
	@Test
	public void create_profile() throws Exception{
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestBusinessCreate.class).addObjects(profile).execute();
	}
	
	@Test
	public void create_profile_with_functions() throws Exception{
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(0l);
		__inject__(FunctionTypeBusiness.class).create(functionType);
		__inject__(FunctionBusiness.class).create(new Function().setCode("f01").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f02").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f03").setName(__getRandomName__()).setType(functionType));
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(3l);
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p01").setName(__getRandomName__())
				.addFunctions(__inject__(FunctionPersistence.class).readByBusinessIdentifier("f01"))
				.addFunctions(__inject__(FunctionPersistence.class).readByBusinessIdentifier("f03"))
				);
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(5l);
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p02").setName(__getRandomName__())
				.addFunctions(__inject__(FunctionPersistence.class).readByBusinessIdentifier("f01"))
				.addFunctions(__inject__(FunctionPersistence.class).readByBusinessIdentifier("f02"))
				.addFunctions(__inject__(FunctionPersistence.class).readByBusinessIdentifier("f03"))
				);
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(8l);
		Profile profile = __inject__(ProfileBusiness.class).findByBusinessIdentifier("p02");
		profile.getFunctions(Boolean.TRUE).removeAll();
		profile.getFunctions(Boolean.TRUE).add(__inject__(FunctionPersistence.class).readByBusinessIdentifier("f02"));
		__inject__(ProfileBusiness.class).update(profile,new Properties().setFields(Profile.FIELD_FUNCTIONS));
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(6l);
		__inject__(ProfileBusiness.class).deleteByBusinessIdentifier("p01");
		assertThat(__inject__(ProfileFunctionPersistence.class).count()).isEqualTo(4l);
	}
	
	@Test
	public void crud_profile_user_with_privileges() throws Exception{
		PrivilegeType privilegeType = new PrivilegeType().setCode("type").setName(__getRandomName__());
		Privilege privilegeModule01 = new Privilege().setCode("m01").setName(__getRandomName__()).setType(privilegeType);
		Privilege privilegeService01 = new Privilege().setCode("s01").setName(__getRandomName__()).setType(privilegeType).addParents(privilegeModule01);
		Privilege privilegeService02 = new Privilege().setCode("s02").setName(__getRandomName__()).setType(privilegeType).addParents(privilegeModule01);
		Privilege privilegeAction01 = new Privilege().setCode("a01").setName(__getRandomName__()).setType(privilegeType).addParents(privilegeService02);
		Privilege privilegeAction02 = new Privilege().setCode("a02").setName(__getRandomName__()).setType(privilegeType).addParents(privilegeService02);
		Privilege privilegeModule02 = new Privilege().setCode("m02").setName(__getRandomName__()).setType(privilegeType);
		Privilege privilegeModule03 = new Privilege().setCode("m03").setName(__getRandomName__()).setType(privilegeType);
		Privilege privilegeService03 = new Privilege().setCode("s03").setName(__getRandomName__()).setType(privilegeType).addParents(privilegeModule03);
		Privilege privilegeAction03 = new Privilege().setCode("a03").setName(__getRandomName__()).setType(privilegeType).addParents(privilegeService03);
		__inject__(PrivilegeTypeBusiness.class).createMany(CollectionHelper.listOf(privilegeType));
		__inject__(PrivilegeBusiness.class).createMany(CollectionHelper.listOf(privilegeModule01,privilegeService01,privilegeService02
				,privilegeAction01,privilegeAction02,privilegeModule02,privilegeModule03,privilegeService03,privilegeAction03));
		Profile profile;
		profile = new Profile().setCode("up01").setName(__getRandomName__());
		__inject__(ProfileBusiness.class).create(profile);
		assertThat(__inject__(ProfilePrivilegePersistence.class).count()).isEqualTo(0l);
		profile = __inject__(ProfileBusiness.class).findByBusinessIdentifier("up01");
		assertThat(profile.getPrivileges()).isNull();
		profile.addPrivilegesByCodes("m01");
		__inject__(ProfileBusiness.class).update(profile,new Properties().setFields(Profile.FIELD_PRIVILEGES));
		assertThat(__inject__(ProfilePrivilegePersistence.class).count()).isEqualTo(1l);
		profile = __inject__(ProfileBusiness.class).findByBusinessIdentifier("up01");
		assertThat(profile.getPrivileges()).isNull();
		profile = __inject__(ProfileBusiness.class).findByBusinessIdentifier("up01",new Properties().setFields(Profile.FIELD_PRIVILEGES));
		assertThat(profile.getPrivileges()).isNotNull();
		assertThat(profile.getPrivileges().get().stream().map(Privilege::getCode).collect(Collectors.toList())).containsOnly("m01");
	}
	
	@Test
	public void create_profileFunction() throws Exception{
		Function function = new Function().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setType(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__()));
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileFunction profileFunction = new ProfileFunction();
		profileFunction.setProfile(profile);
		profileFunction.setFunction(function);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(function.getType(),function,profile).addObjects(profileFunction).execute();
	}
	
	@Test
	public void read_profileByFunctions() throws Exception{
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeBusiness.class).create(functionType);
		__inject__(FunctionBusiness.class).create(new Function().setCode("f01").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f02").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f03").setName(__getRandomName__()).setType(functionType));
		
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p01").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p02").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p03").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p04").setName(__getRandomName__()));
		
		Collection<ProfileFunction> profileFunctions = __inject__(ProfileFunctionBusiness.class).find(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p01"))));
		assertThat(profileFunctions).isEmpty();
		
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p01").setFunctionFromCode("f01"));
		profileFunctions = __inject__(ProfileFunctionBusiness.class).find(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p01"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f03"));
		
		profileFunctions = __inject__(ProfileFunctionBusiness.class).find(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p01"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = __inject__(ProfileFunctionBusiness.class).find(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p02"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f01"));
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionBusiness.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f03"));
		
		profileFunctions = __inject__(ProfileFunctionBusiness.class).find(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p01"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = __inject__(ProfileFunctionBusiness.class).find(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p02"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		profileFunctions = __inject__(ProfileFunctionBusiness.class).find(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p03"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01","f02","f03");
		
		profileFunctions = __inject__(ProfileFunctionBusiness.class).find(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p04"))));
		assertThat(profileFunctions).isEmpty();
	}
	
	@Test
	public void create_user() throws Exception{
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeBusiness.class).create(functionType);
		__inject__(FunctionBusiness.class).create(new Function().setCode("f01").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f02").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		__inject__(FunctionBusiness.class).create(new Function().setCode("f03").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		
		User user = new User().setIdentifier("u01").setFirstName("Komenan").setLastNames("Yao Christian").setElectronicMailAddress("a@b.c");
		__inject__(UserBusiness.class).create(user);
		
		user = __inject__(UserBusiness.class).findBySystemIdentifier("u01");
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isNull();
		assertThat(user.getFunctions()).isNull();
		user = __inject__(UserBusiness.class).findBySystemIdentifier("u01",new Properties().setFields("names,functions"));
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isEqualTo("Komenan Yao Christian");
		assertThat(user.getFunctions()).isNull();
		
		assertThat(user.getFunctions()).isNull();
		user.addFunctionsByCodes("f01");
		assertThat(user.getFunctions()).isNotNull();
		assertThat(user.getFunctions().get()).hasSize(1);
		__inject__(UserBusiness.class).update(user,new Properties().setFields("functions"));
		
		user = __inject__(UserBusiness.class).findBySystemIdentifier("u01");
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isNull();
		assertThat(user.getFunctions()).isNull();
		user = __inject__(UserBusiness.class).findBySystemIdentifier("u01",new Properties().setFields(User.FIELD_FIRST_NAME+",lastNames,names,functions"));
		assertThat(user).isNotNull();
		assertThat(user.getFirstName()).isEqualTo("Komenan");
		assertThat(user.getLastNames()).isEqualTo("Yao Christian");
		assertThat(user.getNames()).isEqualTo("Komenan Yao Christian");
		assertThat(user.getFunctions()).isNotNull();
		assertThat(user.getFunctions().get().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f01");
		
		user.addFunctionsByCodes("f03");
		__inject__(UserBusiness.class).update(user,new Properties().setFields("functions"));
		
		user = __inject__(UserBusiness.class).findBySystemIdentifier("u01");
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isNull();
		assertThat(user.getFunctions()).isNull();
		user = __inject__(UserBusiness.class).findBySystemIdentifier("u01",new Properties().setFields("names,functions"));
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isEqualTo("Komenan Yao Christian");
		assertThat(user.getFunctions()).isNotNull();
		assertThat(user.getFunctions().get().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f01","f03");
		
		user.getFunctions().removeAll();
		user.addFunctionsByCodes("f03");
		__inject__(UserBusiness.class).update(user,new Properties().setFields("functions"));
		user = __inject__(UserBusiness.class).findBySystemIdentifier("u01",new Properties().setFields("names,functions"));
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isEqualTo("Komenan Yao Christian");
		assertThat(user.getFunctions()).isNotNull();
		assertThat(user.getFunctions().get().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f03");
		
		user.getFunctions().removeAll();
		__inject__(UserBusiness.class).update(user,new Properties().setFields("functions"));
		user = __inject__(UserBusiness.class).findBySystemIdentifier("u01",new Properties().setFields("names,functions"));
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isEqualTo("Komenan Yao Christian");
		assertThat(user.getFunctions()).isNull();
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
		
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		__inject__(ScopeTypeBusiness.class).create(scopeType);
		Scope scope = new Scope().setIdentifier("21").setType(scopeType);
		__inject__(ScopeBusiness.class).create(scope);
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeBusiness.class).create(functionType);
		Function function = new Function().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE);
		__inject__(FunctionBusiness.class).create(function);
		__inject__(FunctionBusiness.class).create(new Function().setCode("CE").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		FunctionScope functionScope = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(FunctionScopeBusiness.class).create(functionScope);
		Profile profile = new Profile().setCode("p001").setName(__getRandomName__());
		__inject__(ProfileBusiness.class).create(profile);
		
		UserAccount userAccount = new UserAccount().setIsPersistToKeycloakOnCreate(Boolean.TRUE);
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__())
		.addFunctionsByCodes("CONTROLEUR_FINANCIER");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__());
		userAccount.addFunctionScopes(functionScope);
		userAccount.addProfiles(profile);
		
		assertThat(__inject__(UserFunctionPersistence.class).count()).isEqualTo(0l);
		__inject__(UserAccountBusiness.class).create(userAccount);
		assertThat(__inject__(UserFunctionPersistence.class).count()).isEqualTo(1l);
		
		UserAccount userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier());
		assertThat(userAccount01).isNotNull();
		assertThat(userAccount01.getFunctionScopes()).isNull();
		
		userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(),new Properties().setFields("user,"+User.FIELD_FUNCTIONS));
		assertThat(userAccount01).isNotNull();
		assertThat(userAccount01.getUser().getFunctions()).isNotNull();
		assertThat(userAccount01.getUser().getFunctions().get()).isNotEmpty();
		assertThat(userAccount01.getUser().getFunctions().get().stream().map(Function::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER");
		
		userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_FUNCTION_SCOPES));
		assertThat(userAccount01).isNotNull();
		assertThat(userAccount01.getFunctionScopes()).isNotNull();
		assertThat(userAccount01.getFunctionScopes().get()).isNotEmpty();
		assertThat(userAccount01.getFunctionScopes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(userAccount01.getProfiles()).isNull();
		
		userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_PROFILES));
		assertThat(userAccount01).isNotNull();
		assertThat(userAccount01.getProfiles()).isNotNull();
		assertThat(userAccount01.getProfiles().get()).isNotEmpty();
		assertThat(userAccount01.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001");
		assertThat(userAccount01.getFunctionScopes()).isNull();
		
		userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(),new Properties().setFields(Arrays.asList(UserAccount.FIELD_FUNCTION_SCOPES,UserAccount.FIELD_PROFILES)));
		assertThat(userAccount01).isNotNull();
		assertThat(userAccount01.getFunctionScopes()).isNotNull();
		assertThat(userAccount01.getFunctionScopes().get()).isNotEmpty();
		assertThat(userAccount01.getFunctionScopes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(userAccount01.getProfiles()).isNotNull();
		assertThat(userAccount01.getProfiles().get()).isNotEmpty();
		assertThat(userAccount01.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001");
		
		userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(),new Properties().setFields("user,account,"+User.FIELD_FUNCTIONS));
		userAccount01.getUser().addFunctionsByCodes("CE");
		__inject__(UserAccountBusiness.class).update(userAccount01,new Properties().setFields("functions"));
		userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_USER+","+User.FIELD_FUNCTIONS));
		assertThat(userAccount01).isNotNull();
		assertThat(userAccount01.getUser().getFunctions()).isNotNull();
		assertThat(userAccount01.getUser().getFunctions().get()).isNotEmpty();
		assertThat(userAccount01.getUser().getFunctions().get().stream().map(Function::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER","CE");
		
		userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_USER+","+User.FIELD_FUNCTIONS));
		userAccount01.getUser().getFunctions().removeAll();
		__inject__(UserAccountBusiness.class).update(userAccount01,new Properties().setFields("functions"));
		userAccount01 = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_USER+","+User.FIELD_FUNCTIONS));
		assertThat(userAccount01).isNotNull();
		assertThat(userAccount01.getUser().getFunctions()).isNull();
		
		UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		UserRepresentation userRepresentation = userResource.toRepresentation();
		Map<String,List<String>> attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)CollectionHelper.listOf("21"))
				).hasSize(1);
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}else {
			
		}
	}
	/*
	@Test
	public void create_userAccountProfile() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__());
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
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(FunctionTypeBusiness.class).create(functionType);
		Function function = new Function().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setType(functionType);
		__inject__(FunctionBusiness.class).create(function);
		FunctionScope functionScope = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(FunctionScopeBusiness.class).create(functionScope);
		function = new Function().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setType(functionType);
		__inject__(FunctionBusiness.class).create(function);
		functionScope = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(FunctionScopeBusiness.class).create(functionScope);
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p001").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p002").setName(__getRandomName__()));
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__());
		userAccount.addFunctionScopes(__inject__(FunctionScopePersistence.class).readByBusinessIdentifier("CONTROLEUR_FINANCIER_MINISTERE_21"));
		userAccount.addProfiles(__inject__(ProfilePersistence.class).readByBusinessIdentifier("p001"));
		__inject__(UserAccountBusiness.class).create(userAccount);
		
		UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		UserRepresentation userRepresentation = userResource.toRepresentation();
		Map<String,List<String>> attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)CollectionHelper.listOf("21"))
				).hasSize(1);
		
		userAccount = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
				.add(UserAccount.FIELD_PROFILES,UserAccount.FIELD_FUNCTION_SCOPES)));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getFunctionScopes()).isNotNull();
		assertThat(userAccount.getFunctionScopes().get()).isNotEmpty();
		assertThat(userAccount.getFunctionScopes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(userAccount.getProfiles()).isNotNull();
		assertThat(userAccount.getProfiles().get()).isNotEmpty();
		assertThat(userAccount.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001");
		
		userAccount.addFunctionScopes(__inject__(FunctionScopePersistence.class).readByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_21"));
		userAccount.addProfiles(__inject__(ProfilePersistence.class).readByBusinessIdentifier("p002"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(__inject__(Strings.class).add(UserAccount.FIELD_FUNCTION_SCOPES,UserAccount.FIELD_PROFILES)));
		
		userAccount = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
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
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)CollectionHelper.listOf("21"))
				).hasSize(1);
		
		scope = new Scope().setIdentifier("18").setType(scopeType);
		__inject__(ScopeBusiness.class).create(scope);
		functionScope = new FunctionScope().setFunction(function).setScope(scope);
		__inject__(FunctionScopeBusiness.class).create(functionScope);
		
		userAccount.addFunctionScopes(__inject__(FunctionScopePersistence.class).readByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_18"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(UserAccount.FIELD_FUNCTION_SCOPES));
		
		userAccount = __inject__(UserAccountBusiness.class).findBySystemIdentifier(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
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
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)CollectionHelper.listOf("21","18"))
				).hasSize(1);
		
		__inject__(UserAccountBusiness.class).delete(userAccount);
		__inject__(ProfileBusiness.class).deleteAll();
		__inject__(FunctionScopeBusiness.class).deleteAll();
		__inject__(FunctionBusiness.class).deleteAll();
		__inject__(FunctionTypeBusiness.class).deleteAll();
		__inject__(ScopeBusiness.class).deleteAll();
		__inject__(ScopeTypeBusiness.class).deleteAll();
	}
	
	@Test
	public void create_userAccountInterim() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__());
		
		UserAccount interim = new UserAccount();
		interim.getUser(Boolean.TRUE).setFirstName("Yao").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		interim.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__());
		
		UserAccountInterim userAccountInterim = new UserAccountInterim();
		userAccountInterim.setUserAccount(userAccount);
		userAccountInterim.setInterim(interim);
		userAccountInterim.setFromDate(LocalDateTime.of(2000, 1, 1,0,0));
		userAccountInterim.setToDate(LocalDateTime.of(2000, 2, 1,0,0));
		
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount(),userAccount
				,interim.getUser(),interim.getAccount(),interim).addObjects(userAccountInterim).execute();
	}
	*/
}
