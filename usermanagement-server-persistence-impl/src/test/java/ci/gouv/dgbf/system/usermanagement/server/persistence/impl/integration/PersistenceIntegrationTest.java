package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.protocol.http.HttpHelper;
import org.cyk.utility.__kernel__.test.arquillian.archive.builder.WebArchiveBuilder;
import org.cyk.utility.__kernel__.variable.VariableName;
import org.cyk.utility.server.persistence.query.filter.Filter;
import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTest;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileServiceResource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ServiceResource;

public class PersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTest {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		setProperties(Scope.class, "SECTION", "numero", "libelleLong", "uuid");
		setProperties(Scope.class, "UGP", "code", "libelleLong", "uuid");
		setProperties(Scope.class, "UA", "codeUA", "libelleLong", "uuid");
	}
	
	@Override
	protected void __listenAfter__() {
		super.__listenAfter__();
		HttpHelper.clear();
		ConfigurationHelper.clear();
	}
	
	@Test
	public void create_scopeType() throws Exception{
		__inject__(TestPersistenceCreate.class).addObjects(new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__())).execute();
	}
	
	@Test
	public void create_scope() throws Exception{
		ScopeType scopeType = new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Scope scope = new Scope().setType(scopeType).setCode("1");
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType).addObjects(scope).execute();
	}
	
	@Test
	public void read_scope_many() throws Exception{			
		userTransaction.begin();
		ScopeType scopeType = new ScopeType().setCode("SECTION").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("01"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("02"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("03"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("04"));
		scopeType = new ScopeType().setCode("UGP").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("220670000"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("220680000"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("220690000"));
		scopeType = new ScopeType().setCode("UA").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("011212010001"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("011111010002"));
		userTransaction.commit();
		Collection<Scope> scopes = __inject__(ScopePersistence.class).read();
		assertThat(scopes).isNotNull();
		assertThat(scopes.stream().map(Scope::getName).collect(Collectors.toList())).containsAnyOf("REPRESENTATION NATIONALE","SENAT"
				,"PRESIDENCE DE LA REPUBLIQUE","CONSEIL ECONOMIQUE ET SOCIALE","ECONOMIE NUMERIQUE ET POSTE","PROMOTION DE LA JEUNESSE ","Unité administrative 1","Direction du Budget de l'Etat");
	}
	
	@Test
	public void read_scope_one() throws Exception{
		userTransaction.begin();
		ScopeType scopeType = new ScopeType().setCode("SECTION").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("01"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("02"));
		__inject__(ScopePersistence.class).create(new Scope().setIdentifier("03").setType(scopeType).setCode("03"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("04"));
		scopeType = new ScopeType().setCode("UGP").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("220670000"));
		__inject__(ScopePersistence.class).create(new Scope().setIdentifier("220680000").setType(scopeType).setCode("220680000"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("220690000"));
		scopeType = new ScopeType().setCode("UA").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setIdentifier("011212010001").setType(scopeType).setCode("011212010001"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("011111010002"));
		userTransaction.commit();
		Scope scope = __inject__(ScopePersistence.class).readBySystemIdentifier("03");
		assertThat(scope).isNotNull();
		assertThat(scope.getName()).isEqualTo("PRESIDENCE DE LA REPUBLIQUE");
		
		scope = __inject__(ScopePersistence.class).readBySystemIdentifier("220680000");
		assertThat(scope).isNotNull();
		assertThat(scope.getName()).isEqualTo("EMPLOI DES JEUNES");
		
		scope = __inject__(ScopePersistence.class).readBySystemIdentifier("011212010001");
		assertThat(scope).isNotNull();
		assertThat(scope.getName()).isEqualTo("Direction du Budget de l'Etat");
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
		Scope scope = new Scope().setCode(__getRandomCode__()).setType(scopeType);
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
		__inject__(PrivilegeTypePersistence.class).createMany(CollectionHelper.listOf(privilegeTypeModule,privilegeTypeService,privilegeTypeMenu
				,privilegeTypeAction));
		__inject__(PrivilegeTypeHierarchyPersistence.class).createMany(CollectionHelper.listOf(
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
		assertThat(privilegeType.getChildren()).isNotEmpty();
		assertThat(privilegeType.getChildren().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("service");
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("module",new Properties().setFields(PrivilegeType.FIELD_PARENTS+","+PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNotNull();
		assertThat(privilegeType.getChildren()).isNotEmpty();
		assertThat(privilegeType.getChildren().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("service");
		
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("service");
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("service",new Properties().setFields(PrivilegeType.FIELD_PARENTS));
		assertThat(privilegeType.getParents()).isNotNull();
		assertThat(privilegeType.getParents()).isNotEmpty();
		assertThat(privilegeType.getParents().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("module");
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("service",new Properties().setFields(PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNotNull();
		assertThat(privilegeType.getChildren()).isNotEmpty();
		assertThat(privilegeType.getChildren().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("menu");
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("service",new Properties().setFields(PrivilegeType.FIELD_PARENTS+","+PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNotNull();
		assertThat(privilegeType.getParents()).isNotEmpty();
		assertThat(privilegeType.getParents().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("module");
		assertThat(privilegeType.getChildren()).isNotNull();
		assertThat(privilegeType.getChildren()).isNotEmpty();
		assertThat(privilegeType.getChildren().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("menu");
		
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("action");
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("action",new Properties().setFields(PrivilegeType.FIELD_PARENTS));
		assertThat(privilegeType.getParents()).isNotNull();
		assertThat(privilegeType.getParents()).isNotEmpty();
		assertThat(privilegeType.getParents().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("menu");
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("action",new Properties().setFields(PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNull();
		assertThat(privilegeType.getChildren()).isNull();
		privilegeType = __inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier("action",new Properties().setFields(PrivilegeType.FIELD_PARENTS+","+PrivilegeType.FIELD_CHILDREN));
		assertThat(privilegeType.getParents()).isNotNull();
		assertThat(privilegeType.getParents()).isNotEmpty();
		assertThat(privilegeType.getParents().stream().map(PrivilegeType::getCode).collect(Collectors.toList())).containsOnly("menu");
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
	public void read_profile() throws Exception{
		FunctionType functionType = new FunctionType().setCode(__getRandomCode__()).setName(__getRandomName__());
		PrivilegeType privilegeType = new PrivilegeType().setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileType profileType = new ProfileType().setCode(__getRandomCode__()).setName(__getRandomName__());
		userTransaction.begin();
		__inject__(FunctionTypePersistence.class).create(functionType);
		__inject__(FunctionPersistence.class).create(new Function().setCode("f01").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f02").setName(__getRandomName__()).setType(functionType));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f03").setName(__getRandomName__()).setType(functionType));
		
		__inject__(PrivilegeTypePersistence.class).create(privilegeType);
		__inject__(PrivilegePersistence.class).create(new Privilege().setCode("pvlg01").setName(__getRandomName__()).setType(privilegeType));
		__inject__(PrivilegePersistence.class).create(new Privilege().setCode("pvlg02").setName(__getRandomName__()).setType(privilegeType));
		__inject__(PrivilegePersistence.class).create(new Privilege().setCode("pvlg03").setName(__getRandomName__()).setType(privilegeType));
		__inject__(PrivilegePersistence.class).create(new Privilege().setCode("pvlg04").setName(__getRandomName__()).setType(privilegeType));
		
		__inject__(ProfileTypePersistence.class).create(profileType);
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p01").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p02").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p03").setName(__getRandomName__()).setType(profileType));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p04").setName(__getRandomName__()).setType(profileType));
		userTransaction.commit();
		
		Profile profile = __inject__(ProfilePersistence.class).readByBusinessIdentifier("p01",new Properties().setFields("code,name,type,functions,privileges"));
		assertThat(profile).isNotNull();
		assertThat(profile.getFunctions()).isNull();
		assertThat(profile.getPrivileges()).isNull();
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p01").setFunctionFromCode("f02"));
		userTransaction.commit();
		
		profile = __inject__(ProfilePersistence.class).readByBusinessIdentifier("p01",new Properties().setFields("code,name,type,functions,privileges"));
		assertThat(profile).isNotNull();
		assertThat(profile.getFunctions()).isNotNull();
		assertThat(profile.getFunctions()).isNotEmpty();
		assertThat(profile.getFunctions().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f02");
		assertThat(profile.getPrivileges()).isNull();
		
		userTransaction.begin();
		__inject__(ProfilePrivilegePersistence.class).create(new ProfilePrivilege().setProfileFromCode("p01").setPrivilegeFromCode("pvlg03"));
		__inject__(ProfilePrivilegePersistence.class).create(new ProfilePrivilege().setProfileFromCode("p01").setPrivilegeFromCode("pvlg01"));
		userTransaction.commit();
		
		profile = __inject__(ProfilePersistence.class).readByBusinessIdentifier("p01",new Properties().setFields("code,name,type,functions,privileges"));
		assertThat(profile).isNotNull();
		assertThat(profile.getFunctions()).isNotNull();
		assertThat(profile.getFunctions()).isNotEmpty();
		assertThat(profile.getFunctions().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f02");
		assertThat(profile.getPrivileges()).isNotNull();
		assertThat(profile.getPrivileges()).isNotEmpty();
		assertThat(profile.getPrivileges().stream().map(Privilege::getCode).collect(Collectors.toList())).containsOnly("pvlg03","pvlg01");
		
		userTransaction.begin();
		__inject__(ProfilePrivilegePersistence.class).deleteAll();
		__inject__(ProfileFunctionPersistence.class).deleteAll();
		userTransaction.commit();
		
		profile = __inject__(ProfilePersistence.class).readByBusinessIdentifier("p01",new Properties().setFields("functions,privileges"));
		assertThat(profile.getFunctions()).isNull();
		assertThat(profile.getPrivileges()).isNull();
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
	public void read_profile_filter_byTypes() throws Exception{
		userTransaction.begin();
		__inject__(ProfileTypePersistence.class).createMany(Arrays.asList(new ProfileType().setCode("T01").setName(__getRandomName__()),
				new ProfileType().setCode("T02").setName(__getRandomName__())));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p01").setName(__getRandomName__()).setTypeFromCode("T01"));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p02").setName(__getRandomName__()).setTypeFromCode("T01"));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p03").setName(__getRandomName__()).setTypeFromCode("T01"));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p04").setName(__getRandomName__()).setTypeFromCode("T02"));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p05").setName(__getRandomName__()).setTypeFromCode("T02"));
		userTransaction.commit();
		
		Collection<Profile> profiles = __inject__(ProfilePersistence.class).read();
		assertThat(profiles).isNotNull();
		assertThat(profiles.stream().map(Profile::getCode).collect(Collectors.toList())).containsOnly("p01","p02","p03","p04","p05");
		
		Filter filter = __inject__(Filter.class).setKlass(Profile.class).addField(Profile.FIELD_TYPE, Arrays.asList("T01"));
		profiles = __inject__(ProfilePersistence.class).read(new Properties().setQueryFilters(filter));
		assertThat(profiles).isNotNull();
		assertThat(profiles.stream().map(Profile::getCode).collect(Collectors.toList())).containsOnly("p01","p02","p03");
		
		filter = __inject__(Filter.class).setKlass(Profile.class).addField(Profile.FIELD_TYPE, Arrays.asList("T02"));
		profiles = __inject__(ProfilePersistence.class).read(new Properties().setQueryFilters(filter));
		assertThat(profiles).isNotNull();
		assertThat(profiles.stream().map(Profile::getCode).collect(Collectors.toList())).containsOnly("p04","p05");
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
		assertThat(user.getFunctions().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f01");
		
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
		assertThat(user.getFunctions().stream().map(Function::getCode).collect(Collectors.toList())).containsOnly("f01","f03");
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
	public void create_userAccount_withScope() throws Exception{
		userTransaction.begin();
		__inject__(ScopeTypePersistence.class).create(new ScopeType().setCode("st01").setName(__getRandomName__()));
		__inject__(ScopePersistence.class).createMany(Arrays.asList(
				new Scope().setIdentifier("s01").setCode("s01").setName(__getRandomName__()).setTypeFromCode("st01")
				,new Scope().setIdentifier("s02").setCode("s02").setName(__getRandomName__()).setTypeFromCode("st01")
				));
		
		UserAccount userAccount = new UserAccount().setIdentifier("ua01").setIsPersistToKeycloakOnCreate(Boolean.FALSE);
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		__inject__(UserPersistence.class).create(userAccount.getUser());
		__inject__(AccountPersistence.class).create(userAccount.getAccount());
		__inject__(UserAccountPersistence.class).create(userAccount);
		userTransaction.commit();	
		
		userAccount = __inject__(UserAccountPersistence.class).readBySystemIdentifier("ua01", new Properties().setFields("identifier,scopes"));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getScopes()).isNull();
		
		userTransaction.begin();
		__inject__(UserAccountScopePersistence.class).createMany(Arrays.asList(
				new UserAccountScope().setUserAccount(userAccount).setScopeFromIdentifier("s01")
				));
		userTransaction.commit();	
		
		userAccount = __inject__(UserAccountPersistence.class).readBySystemIdentifier("ua01", new Properties().setFields("identifier,scopes"));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getScopes()).isNotNull();
		assertThat(userAccount.getScopes()).hasSize(1);
		assertThat(userAccount.getScopes().stream().map(Scope::getIdentifier).collect(Collectors.toList())).containsOnly("s01");
		
		userTransaction.begin();
		__inject__(UserAccountScopePersistence.class).createMany(Arrays.asList(
				new UserAccountScope().setUserAccount(userAccount).setScopeFromIdentifier("s02")
				));
		userTransaction.commit();	
		
		userAccount = __inject__(UserAccountPersistence.class).readBySystemIdentifier("ua01", new Properties().setFields("identifier,scopes"));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getScopes()).isNotNull();
		assertThat(userAccount.getScopes()).hasSize(2);
		assertThat(userAccount.getScopes().stream().map(Scope::getIdentifier).collect(Collectors.toList())).containsOnly("s01","s02");
	}
	
	@Test
	public void read_userAccount_byAccountIdentifier() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier("cyk").setPass("123");
		userTransaction.begin();
		__inject__(UserPersistence.class).create(userAccount.getUser());
		__inject__(AccountPersistence.class).create(userAccount.getAccount());
		__inject__(UserAccountPersistence.class).create(userAccount);
		userTransaction.commit();
		userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier("yao").setPass("123");
		userTransaction.begin();
		__inject__(UserPersistence.class).create(userAccount.getUser());
		__inject__(AccountPersistence.class).create(userAccount.getAccount());
		__inject__(UserAccountPersistence.class).create(userAccount);
		userTransaction.commit();
		Collection<UserAccount> userAccounts = __inject__(UserAccountPersistence.class).read(new Properties().setQueryFilters(__inject__(Filter.class)
					.setKlass(UserAccount.class).addField("account.identifier", "yao")));
		assertThat(userAccounts).isNotNull();
		assertThat(userAccounts.stream().map(x -> x.getAccount().getIdentifier())).containsExactly("yao");
		
		userAccounts = __inject__(UserAccountPersistence.class).read(new Properties().setQueryFilters(__inject__(Filter.class)
				.addField("account.identifier", "cyk")));
		assertThat(userAccounts).isNotNull();
		assertThat(userAccounts.stream().map(x -> x.getAccount().getIdentifier())).containsExactly("cyk");
		
		userAccounts = __inject__(UserAccountPersistence.class).read(new Properties().setQueryFilters(__inject__(Filter.class)
				.addField("account.identifier", "abc")));
		assertThat(userAccounts).isEmpty();
	}
	
	@Test
	public void create_userAccountFunctionScope_ministryIs21() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		Scope scope = new Scope().setCode("21").setType(scopeType);
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
			.setIsCatchThrowable(Boolean.FALSE).execute();
	}
	
	@Test
	public void create_userAccountFunctionScope_programIs100() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("PROGRAMME").setName("Programme");
		Scope scope = new Scope().setCode("100").setType(scopeType);
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
			.setIsCatchThrowable(Boolean.FALSE).execute();
	}
	
	@Test
	public void create_userAccountFunctionScope_administrativeUnitIs200() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("UNITE_ADMINISTRATIVE").setName("Unité administrative");
		Scope scope = new Scope().setCode("200").setType(scopeType);
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
			.setIsCatchThrowable(Boolean.FALSE).execute();
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
	
	/**/
	
	private void setProperties(Class<?> klass,Object classifier,String code,String name,String link) {		
		System.setProperty(VariableName.buildClassUniformResourceIdentifier(klass,classifier), "http://localhost:10000/"+classifier.toString().toLowerCase());
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_CODE), code);
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_NAME), name);
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_LINK), link);
	}
	
	@org.jboss.arquillian.container.test.api.Deployment
	public static WebArchive createArchive(){
		return new WebArchiveBuilder().execute()
				.addAsResource("ci/gouv/dgbf/system/usermanagement/server/persistence/impl/integration/section.json")
				.addAsResource("ci/gouv/dgbf/system/usermanagement/server/persistence/impl/integration/ugp.json")
				.addAsResource("ci/gouv/dgbf/system/usermanagement/server/persistence/impl/integration/ua.json")
				; 
	}
}
