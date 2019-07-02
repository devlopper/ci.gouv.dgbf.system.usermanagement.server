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
import org.cyk.utility.map.MapHelper;
import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

public class PersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
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
	public void create_functionCategory() throws Exception{
		__inject__(TestPersistenceCreate.class).addObjects(new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__())).execute();
	}
	
	@Test
	public void create_function() throws Exception{
		Function function = new Function().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setCategory(new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__()));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(function.getCategory()).addObjects(function).execute();
	}
	
	@Test
	public void create_functionScope() throws Exception{
		ScopeType scopeType = new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Scope scope = new Scope().setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setScope(scope).setCode(__getRandomCode__()).setName(__getRandomName__());
		functionScope.setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__())));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionScope.getFunction().getCategory(),functionScope.getFunction()).addObjects(functionScope).execute();
	}
	
	@Test
	public void create_profile() throws Exception{
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjects(profile).execute();
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
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(function.getCategory(),function,profile).addObjects(profileFunction).execute();
	}
	
	@Test
	public void read_profileByFunctions() throws Exception{
		FunctionCategory category = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		userTransaction.begin();
		__inject__(FunctionCategoryPersistence.class).create(category);
		__inject__(FunctionPersistence.class).create(new Function().setCode("f01").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f02").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f03").setName(__getRandomName__()).setCategory(category));
		
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p01").setName(__getRandomName__()));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p02").setName(__getRandomName__()));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p03").setName(__getRandomName__()));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p04").setName(__getRandomName__()));
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
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).deleteAll();
		__inject__(ProfilePersistence.class).deleteAll();
		__inject__(FunctionPersistence.class).deleteAll();
		userTransaction.commit();
	}
	
	@Test
	public void read_profileFunction_filter_by_profiles() throws Exception{
		FunctionCategory category = new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		userTransaction.begin();
		__inject__(FunctionCategoryPersistence.class).create(category);
		__inject__(FunctionPersistence.class).create(new Function().setCode("f01").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f02").setName(__getRandomName__()).setCategory(category));
		__inject__(FunctionPersistence.class).create(new Function().setCode("f03").setName(__getRandomName__()).setCategory(category));
		
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p01").setName(__getRandomName__()));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p02").setName(__getRandomName__()));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p03").setName(__getRandomName__()));
		__inject__(ProfilePersistence.class).create(new Profile().setCode("p04").setName(__getRandomName__()));
		userTransaction.commit();
		
		Collection<ProfileFunction> profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isEmpty();
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p01").setFunctionFromCode("f01"));
		userTransaction.commit();
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_FUNCTION, "f01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p01");
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p02").setFunctionFromCode("f03"));
		userTransaction.commit();
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p02")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f01"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f02"));
		__inject__(ProfileFunctionPersistence.class).create(new ProfileFunction().setProfileFromCode("p03").setFunctionFromCode("f03"));
		userTransaction.commit();
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p02")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f02","f03");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p03")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getFunction().getCode())).containsOnly("f01","f02","f03");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_PROFILE, "p04")));
		assertThat(profileFunctions).isEmpty();
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_FUNCTION, "f01")));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode())).containsOnly("p01","p03");
		
		profileFunctions = __inject__(ProfileFunctionPersistence.class).read(new Properties().setQueryFilters(__inject__(MapHelper.class).instanciate(ProfileFunction.FIELD_FUNCTION, Arrays.asList("f01","f02"))));
		assertThat(profileFunctions).isNotEmpty();
		assertThat(profileFunctions.stream().map(x -> x.getProfile().getCode()).collect(Collectors.toSet())).containsOnly("p01","p02","p03");
		
		userTransaction.begin();
		__inject__(ProfileFunctionPersistence.class).deleteAll();
		__inject__(ProfilePersistence.class).deleteAll();
		__inject__(FunctionPersistence.class).deleteAll();
		userTransaction.commit();
		
	}
	
	@Test
	public void create_userAccount() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount()).addObjects(userAccount).execute();
	}
	
	@Test
	public void create_userAccountProfile() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		UserAccountProfile userAccountProfile = new UserAccountProfile().setUserAccount(userAccount).setProfile(profile);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount(),userAccount,profile).addObjects(userAccountProfile).execute();
	}
	
	@Test
	public void create_userAccountFunctionScope_ministryIs21() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		Scope scope = new Scope().setIdentifier("21").setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setCode(__getRandomCode__()).setName(__getRandomName__()).setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(scope);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountFunctionScope = new UserAccountFunctionScope();
		userAccountFunctionScope.setUserAccount(userAccount);
		userAccountFunctionScope.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionScope.getFunction().getCategory(),functionScope.getFunction()
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
				.setCategory(new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(scope);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountFunctionScope = new UserAccountFunctionScope();
		userAccountFunctionScope.setUserAccount(userAccount);
		userAccountFunctionScope.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionScope.getFunction().getCategory(),functionScope.getFunction()
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
				.setCategory(new FunctionCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(scope);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountFunctionScope = new UserAccountFunctionScope();
		userAccountFunctionScope.setUserAccount(userAccount);
		userAccountFunctionScope.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,scope,functionScope.getFunction().getCategory(),functionScope.getFunction()
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
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount(),userAccount
				,interim.getUser(),interim.getAccount(),interim).addObjects(userAccountInterim).execute();
	}
	
}
