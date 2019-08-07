package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

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
import org.cyk.utility.server.persistence.query.filter.Filter;
import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileServiceResource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ServiceResource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

public class PersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
		//AbstractPersistenceFunctionImpl.LOG_LEVEL = LogLevel.INFO;
		super.__listenBeforeCallCountIsZero__();	
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		
	}
	
	@Test
	public void create_scopeType() throws Exception{
		__inject__(TestPersistenceCreate.class).addObjects(new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__())).execute();
	}
	
	@Test
	public void create_scope() throws Exception{
		ScopeType scopeType = new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Scope scope = new Scope().setType(scopeType);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType).addObjects(scope).execute();
	}
	
	@Test
	public void create_functionType() throws Exception{
		__inject__(TestPersistenceCreate.class).addObjects(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__())).execute();
	}
	
	@Test
	public void create_function() throws Exception{
		Function function = new Function().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setType(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__()));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(function.getType()).addObjects(function).execute();
	}
	
	@Test
	public void create_functionScope() throws Exception{
		ScopeType scopeType = new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Scope scope = new Scope().setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setScope(scope).setCode(__getRandomCode__()).setName(__getRandomName__());
		functionScope.setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setType(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__())));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionScope.getFunction().getType(),functionScope.getFunction()).addObjects(functionScope).execute();
	}
	
	@Test
	public void create_privilegeType() throws Exception{
		userTransaction.begin();
		PrivilegeType privilegeTypeModule = new PrivilegeType().setCode("module").setName(__getRandomName__());
		PrivilegeType privilegeTypeService = new PrivilegeType().setCode("service").setName(__getRandomName__()).addParents(privilegeTypeModule);
		PrivilegeType privilegeTypeMenu = new PrivilegeType().setCode("menu").setName(__getRandomName__()).addParents(privilegeTypeService);
		PrivilegeType privilegeTypeAction = new PrivilegeType().setCode("action").setName(__getRandomName__()).addParents(privilegeTypeMenu);
		__inject__(PrivilegeTypePersistence.class).createMany(__inject__(CollectionHelper.class).instanciate(privilegeTypeModule,privilegeTypeService,privilegeTypeMenu
				,privilegeTypeAction));
		__inject__(PrivilegeTypeHierarchyPersistence.class).createMany(__inject__(CollectionHelper.class).instanciate(
				new PrivilegeTypeHierarchy().setParent(privilegeTypeModule).setChild(privilegeTypeService)
				,new PrivilegeTypeHierarchy().setParent(privilegeTypeService).setChild(privilegeTypeMenu)
				,new PrivilegeTypeHierarchy().setParent(privilegeTypeMenu).setChild(privilegeTypeAction)
				));
		userTransaction.commit();
		PrivilegeType privilegeType;
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("module");
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("module",new Properties().setFields(PrivilegeType.FIELD_PARENTS));
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("module",new Properties().setFields(PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNotNull();
		assertThat(privilegeType.getChildren().get()).isNotEmpty();
		assertThat(privilegeType.getChildren().get().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("service");
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("module",new Properties().setFields(PrivilegeType.FIELD_PARENTS+","+PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNotNull();
		assertThat(privilegeType.getChildren().get()).isNotEmpty();
		assertThat(privilegeType.getChildren().get().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("service");
		
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("service");
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("service",new Properties().setFields(PrivilegeType.FIELD_PARENTS));
		assertThat(privilegeType.getParents()).isNotNull();
		assertThat(privilegeType.getParents().get()).isNotEmpty();
		assertThat(privilegeType.getParents().get().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("module");
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("service",new Properties().setFields(PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNotNull();
		assertThat(privilegeType.getChildren().get()).isNotEmpty();
		assertThat(privilegeType.getChildren().get().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("menu");
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("service",new Properties().setFields(PrivilegeType.FIELD_PARENTS+","+PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNotNull();
		assertThat(privilegeType.getParents().get()).isNotEmpty();
		assertThat(privilegeType.getParents().get().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("module");
		assertThat(privilegeType.getChildren()).isNotNull();
		assertThat(privilegeType.getChildren().get()).isNotEmpty();
		assertThat(privilegeType.getChildren().get().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("menu");
		
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("action");
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("action",new Properties().setFields(PrivilegeType.FIELD_PARENTS));
		assertThat(privilegeType.getParents()).isNotNull();
		assertThat(privilegeType.getParents().get()).isNotEmpty();
		assertThat(privilegeType.getParents().get().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("menu");
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("action",new Properties().setFields(PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("action",new Properties().setFields(PrivilegeType.FIELD_PARENTS+","+PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNotNull();
		assertThat(privilegeType.getParents().get()).isNotEmpty();
		assertThat(privilegeType.getParents().get().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("menu");
		assertThat(privilegeType.getChildren()).isNull();
	}
	
	@Test
	public void create_profile() throws Exception{
		ProfileType profileType = new ProfileType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__()).setType(profileType);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(profileType).addObjects(profile).execute();
	}
	
	@Test
	public void create_profileFunction() throws Exception{
		Function function = new Function().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setType(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__()));
		ProfileType profileType = new ProfileType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__()).setType(profileType);
		ProfileFunction profileFunction = new ProfileFunction();
		profileFunction.setProfile(profile);
		profileFunction.setFunction(function);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(function.getType(),function,profileType,profile).addObjects(profileFunction).execute();
	}
	
	@Test
	public void create_service() throws Exception{
		__inject__(TestPersistenceCreate.class).addObjects(new Service().setUrl("url")).execute();
	}
	
	@Test
	public void create_resource() throws Exception{
		__inject__(TestPersistenceCreate.class).addObjects(new Resource().setCode(__getRandomCode__()).setName(__getRandomName__()).setUrl("url")).execute();
	}
	
	@Test
	public void create_serviceResource() throws Exception{
		ServiceResource serviceResource = new ServiceResource();
		serviceResource.setService(new Service().setUrl("url"));
		serviceResource.setResource(new Resource().setCode(__getRandomCode__()).setName(__getRandomName__()).setUrl("url"));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(serviceResource.getService(),serviceResource.getResource()).addObjects(serviceResource).execute();
	}
	
	@Test
	public void create_profileServiceResource() throws Exception{
		assertThat(__inject__(ProfileServiceResourcePersistence.class).count()).isEqualTo(0l);
		ProfileServiceResource profileServiceResource = new ProfileServiceResource();
		profileServiceResource.setProfile(new Profile().setCode(__getRandomCode__()).setName(__getRandomName__()).setType(new ProfileType().setCode(__getRandomCode__()).setName(__getRandomName__())));
		profileServiceResource.setService(new Service().setUrl("url"));
		profileServiceResource.setResource(new Resource().setCode(__getRandomCode__()).setName(__getRandomName__()).setUrl("url"));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(profileServiceResource.getProfile().getType(),profileServiceResource.getProfile(),profileServiceResource.getService()
				,profileServiceResource.getResource()).addObjects(profileServiceResource).execute();
		assertThat(__inject__(ProfileServiceResourcePersistence.class).count()).isEqualTo(0l);
	}
	
	@Test
	public void read_profileByFunctions() throws Exception{
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileType profileType = new ProfileType().setCode(__getRandomCode__()).setName(__getRandomName__());
		userTransaction.begin();
		__inject__(FunctionTypePersistence.class).create(functionType);
		__inject__(FunctionPersistence.class).create(new Function().setCode("f01").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f02").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f03").setName(__getRandomName__()).setType(functionType));
		
		__inject__(ProfileTypePersistence.class).create(profileType);
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p01").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p02").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p03").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p04").setName(__getRandomName__()).setType(profileType));
		userTransaction.commit();
		
		Collection<ProfileFunction> profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfileCodes("p01");
		assertThat(profileFunctions).isEmpty();
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p01").setFunctionFromCode("f01"));
		userTransaction.commit();
		profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfileCodes("p01");
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f03"));
		userTransaction.commit();
		profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfileCodes("p01");
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfileCodes("p02");
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f01"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f03"));
		userTransaction.commit();
		profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfileCodes("p01");
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfileCodes("p02");
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfileCodes("p03");
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01","f02","f03");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfileCodes("p04");
		assertThat(profileFunctions).isEmpty();
	}
	
	@Test
	public void read_profileFunction_filter_by_profiles() throws Exception{
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileType profileType = new ProfileType().setCode(__getRandomCode__()).setName(__getRandomName__());
		userTransaction.begin();
		__inject__(FunctionTypePersistence.class).create(functionType);
		__inject__(FunctionPersistence.class).create(new Function().setCode("f01").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f02").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f03").setName(__getRandomName__()).setType(functionType));
		
		__inject__(ProfileTypePersistence.class).create(profileType);
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p01").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p02").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p03").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p04").setName(__getRandomName__()).setType(profileType));
		userTransaction.commit();
		
		Filter filters = __inject__(Filter.class).setKlass(ProfileFunction.class);
		filters.addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p01"));
		Collection<ProfileFunction> profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(filters));
		assertThat(profileFunctions).isEmpty();
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p01").setFunctionFromCode("f01"));
		userTransaction.commit();
		filters = __inject__(Filter.class).setKlass(ProfileFunction.class);
		filters.addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p01"));
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(filters));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		filters = __inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_FUNCTION,Arrays.asList("f01"));
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(filters));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p01");
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f03"));
		userTransaction.commit();
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p01"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p02"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f01"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f03"));
		userTransaction.commit();
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p01"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p02"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p03"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01","f02","f03");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_PROFILE,Arrays.asList("p04"))));
		assertThat(profileFunctions).isEmpty();
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_FUNCTION,Arrays.asList("f01"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p01","p03");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties()
				.setQueryFilters(__inject__(Filter.class).setKlass(ProfileFunction.class).addField(ProfileFunction.FIELD_FUNCTION,Arrays.asList("f01","f02"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode()).collect(Collectors.toSet())).containsOnly("p01","p02","p03");	
	}
	
	@Test
	public void create_user() throws Exception{
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		userTransaction.begin();
		__inject__(FunctionTypePersistence.class).create(functionType);
		__inject__(FunctionPersistence.class).create(new Function().setCode("f01").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f02").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f03").setName(__getRandomName__()).setType(functionType).setIsProfileCreatableOnCreate(Boolean.FALSE));
		userTransaction.commit();
		
		User user = new User().setIdentifier("u01").setFirstName("Komenan").setLastNames("Yao Christian").setElectronicMailAddress("a@b.c");
		userTransaction.begin();
		__inject__(UserPersistence.class).create(user);
		userTransaction.commit();
		
		user = __inject__(UserPersistence.class).readBySystemIdentifier("u01");
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isNull();
		assertThat(user.getFunctions()).isNull();
		user = __inject__(UserPersistence.class).readBySystemIdentifier("u01",new Properties().setFields("names,functions"));
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isEqualTo("Komenan Yao Christian");
		assertThat(user.getFunctions()).isNull();
		
		userTransaction.begin();
		__inject__(UserFunctionPersistence.class).create(new UserFunction().setUserByIdentifier("u01").setFunctionByCode("f01"));
		userTransaction.commit();
		
		user = __inject__(UserPersistence.class).readBySystemIdentifier("u01");
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isNull();
		assertThat(user.getFunctions()).isNull();
		user = __inject__(UserPersistence.class).readBySystemIdentifier("u01",new Properties().setFields("names,functions"));
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isEqualTo("Komenan Yao Christian");
		assertThat(user.getFunctions()).isNotNull();
		assertThat(user.getFunctions().get().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f01");
		
		userTransaction.begin();
		__inject__(UserFunctionPersistence.class).create(new UserFunction().setUserByIdentifier("u01").setFunctionByCode("f03"));
		userTransaction.commit();
		
		user = __inject__(UserPersistence.class).readBySystemIdentifier("u01");
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isNull();
		assertThat(user.getFunctions()).isNull();
		user = __inject__(UserPersistence.class).readBySystemIdentifier("u01",new Properties().setFields("names,functions"));
		assertThat(user).isNotNull();
		assertThat(user.getNames()).isEqualTo("Komenan Yao Christian");
		assertThat(user.getFunctions()).isNotNull();
		assertThat(user.getFunctions().get().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f01","f03");
	}
	
	@Test
	public void create_userAccount() throws Exception{
		UserAccount userAccount = new UserAccount().setIsPersistToKeycloakOnCreate(Boolean.FALSE);
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount()).addObjects(userAccount).execute();
	}
	
	@Test
	public void create_userAccountProfile() throws Exception{
		UserAccount userAccount = new UserAccount().setIsPersistToKeycloakOnCreate(Boolean.FALSE);
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__()).setType(new ProfileType().setCode(__getRandomCode__()).setName(__getRandomName__()));
		UserAccountProfile userAccountProfile = new UserAccountProfile().setUserAccount(userAccount).setProfile(profile);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount(),userAccount,profile.getType(),profile).addObjects(userAccountProfile).execute();
	}
	
	@Test
	public void create_userAccountFunctionScope_ministryIs21() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		Scope scope = new Scope().setIdentifier("21").setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setCode(__getRandomCode__()).setName(__getRandomName__()).setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setType(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(scope);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountFunctionScope = new UserAccountFunctionScope();
		userAccountFunctionScope.setUserAccount(userAccount);
		userAccountFunctionScope.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionScope.getFunction().getType(),functionScope.getFunction()
				,functionScope,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountFunctionScope)
			.setIsCatchThrowable(Boolean.FALSE).addTryEndRunnables(new Runnable() {
				@Override
				public void run() {
					UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
					UserRepresentation userRepresentation = userResource.toRepresentation();
					Map<String,List<String>> attributes = userRepresentation.getAttributes();
					assertThat(attributes).containsExactly(new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE"
							,(List<String>)__inject__(CollectionHelper.class).instanciate("21")));
				}
			}).execute();
	}
	
	@Test
	public void create_userAccountFunctionScope_programIs100() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("PROGRAMME").setName("Programme");
		Scope scope = new Scope().setIdentifier("100").setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setCode(__getRandomCode__()).setName(__getRandomName__());
		functionScope.setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setType(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(scope);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountFunctionScope = new UserAccountFunctionScope();
		userAccountFunctionScope.setUserAccount(userAccount);
		userAccountFunctionScope.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionScope.getFunction().getType(),functionScope.getFunction()
				,functionScope,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountFunctionScope)
			.setIsCatchThrowable(Boolean.FALSE).addTryEndRunnables(new Runnable() {
				@Override
				public void run() {
					UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
					UserRepresentation userRepresentation = userResource.toRepresentation();
					Map<String,List<String>> attributes = userRepresentation.getAttributes();
					assertThat(attributes).containsExactly(new AbstractMap.SimpleEntry<String, List<String>>("PROGRAMME"
							,(List<String>)__inject__(CollectionHelper.class).instanciate("100")));
				}
			}).execute();
	}
	
	@Test
	public void create_userAccountFunctionScope_administrativeUnitIs200() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("UNITE_ADMINISTRATIVE").setName("Unité administrative");
		Scope scope = new Scope().setIdentifier("200").setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setCode(__getRandomCode__()).setName(__getRandomName__());
		functionScope.setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setType(new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(scope);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountFunctionScope = new UserAccountFunctionScope();
		userAccountFunctionScope.setUserAccount(userAccount);
		userAccountFunctionScope.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionScope.getFunction().getType(),functionScope.getFunction()
				,functionScope,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountFunctionScope)
			.setIsCatchThrowable(Boolean.FALSE).addTryEndRunnables(new Runnable() {
				@Override
				public void run() {
					UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
					UserRepresentation userRepresentation = userResource.toRepresentation();
					Map<String,List<String>> attributes = userRepresentation.getAttributes();
					assertThat(attributes).containsExactly(new AbstractMap.SimpleEntry<String, List<String>>("UNITE_ADMINISTRATIVE"
							,(List<String>)__inject__(CollectionHelper.class).instanciate("200")));
				}
			}).execute();
	}
	
	@Test
	public void create_userAccountInterim() throws Exception{
		UserAccount userAccount = new UserAccount().setIsPersistToKeycloakOnCreate(Boolean.FALSE);
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
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount(),userAccount
				,interim.getUser(),interim.getAccount(),interim).addObjects(userAccountInterim).execute();
	}
	
}
