package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
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
	public void create_posteLocationType() throws Exception{
		ScopeType posteLocationType = new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjects(posteLocationType).execute();
	}
	
	@Test
	public void create_posteLocation() throws Exception{
		ScopeType posteLocationType = new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Scope posteLocation = new Scope().setType(posteLocationType);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(posteLocationType).addObjects(posteLocation).execute();
	}
	
	@Test
	public void create_roleCategory() throws Exception{
		RoleCategory role = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjects(role).execute();
	}
	
	@Test
	public void create_function() throws Exception{
		Function role = new Function().setCode(__getRandomCode__()).setName(__getRandomName__());
		role.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(role.getCategory()).addObjects(role).execute();
	}
	
	@Test
	public void create_functionScope() throws Exception{
		ScopeType scopeType = new ScopeType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Scope location = new Scope().setType(scopeType);
		FunctionScope role = new FunctionScope().setScope(location).setCode(__getRandomCode__()).setName(__getRandomName__());
		role.setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__())));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,location,role.getFunction().getCategory(),role.getFunction()).addObjects(role).execute();
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
		function.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()));
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileFunction profileFunction = new ProfileFunction();
		profileFunction.setProfile(profile);
		profileFunction.setFunction(function);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(function.getCategory(),function,profile).addObjects(profileFunction).execute();
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
	public void create_userAccountRolePoste_ministryIs21() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		Scope location = new Scope().setIdentifier("21").setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setCode(__getRandomCode__()).setName(__getRandomName__()).setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(location);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountRolePoste = new UserAccountFunctionScope();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,location,functionScope.getFunction().getCategory(),functionScope.getFunction()
				,functionScope,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
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
	public void create_userAccountRolePoste_programIs100() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("PROGRAMME").setName("Programme");
		Scope location = new Scope().setIdentifier("100").setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setCode(__getRandomCode__()).setName(__getRandomName__());
		functionScope.setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(location);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountRolePoste = new UserAccountFunctionScope();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,location,functionScope.getFunction().getCategory(),functionScope.getFunction()
				,functionScope,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
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
	public void create_userAccountRolePoste_administrativeUnitIs200() throws Exception{
		ScopeType scopeType = new ScopeType().setCode("UNITE_ADMINISTRATIVE").setName("Unité administrative");
		Scope location = new Scope().setIdentifier("200").setType(scopeType);
		FunctionScope functionScope = new FunctionScope().setCode(__getRandomCode__()).setName(__getRandomName__());
		functionScope.setFunction(new Function().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setScope(location);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountFunctionScope userAccountRolePoste = new UserAccountFunctionScope();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setFunctionScope(functionScope);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(scopeType,location,functionScope.getFunction().getCategory(),functionScope.getFunction()
				,functionScope,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
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
