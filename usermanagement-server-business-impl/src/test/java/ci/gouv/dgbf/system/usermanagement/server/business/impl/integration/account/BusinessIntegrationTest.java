package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
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
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
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
	public void create_roleCategory() throws Exception{
		RoleCategory role = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomCode__());
		__inject__(TestBusinessCreate.class).addObjects(role).execute();
	}
	
	@Test
	public void create_roleFunction() throws Exception{
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		Function role = new Function().setCode(__getRandomCode__()).setName(__getRandomCode__()).setCategory(roleCategory);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(roleCategory).addObjects(role).execute();
	}
	
	@Test
	public void create_rolePoste() throws Exception{
		ScopeType locationType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		Scope location = new Scope().setIdentifier("21").setType(locationType);
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		Function roleFunction = new Function().setCode("ASSISTANT").setName("Assistant").setCategory(roleCategory);
		FunctionScope role = new FunctionScope().setFunction(roleFunction).setScope(location);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(locationType,location,roleCategory,roleFunction).addObjects(role).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				assertThat(role.getCode()).isEqualTo("ASSISTANT_MINISTERE_21");
				assertThat(role.getName()).isEqualTo("Assistant du ministère 21");
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
	public void create_profileRoleFunction() throws Exception{
		Function function = new Function().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()));
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileFunction profileRoleFunction = new ProfileFunction();
		profileRoleFunction.setProfile(profile);
		profileRoleFunction.setFunction(function);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(function.getCategory(),function,profile).addObjects(profileRoleFunction).execute();
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
		
		ScopeType locationType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		__inject__(ScopeTypeBusiness.class).create(locationType);
		Scope location = new Scope().setIdentifier("21").setType(locationType);
		__inject__(ScopeBusiness.class).create(location);
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(RoleCategoryBusiness.class).create(roleCategory);
		Function roleFunction = new Function().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(FunctionBusiness.class).create(roleFunction);
		FunctionScope rolePoste = new FunctionScope().setFunction(roleFunction).setScope(location);
		__inject__(FunctionScopeBusiness.class).create(rolePoste);
		Profile profile = new Profile().setCode("p001").setName(__getRandomName__());
		__inject__(ProfileBusiness.class).create(profile);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostes(rolePoste);
		userAccount.addProfiles(profile);
		__inject__(TestBusinessCreate.class).setName("Create user account").addObjects(userAccount).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				UserAccount userAccount01 = __inject__(UserAccountBusiness.class).findOneBySystemIdentifier(userAccount.getIdentifier());
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getPostes()).isNull();
				
				userAccount01 = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_POSTES));
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getPostes()).isNotNull();
				assertThat(userAccount01.getPostes().get()).isNotEmpty();
				assertThat(userAccount01.getPostes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
				assertThat(userAccount01.getProfiles()).isNull();
				
				userAccount01 = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_PROFILES));
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getProfiles()).isNotNull();
				assertThat(userAccount01.getProfiles().get()).isNotEmpty();
				assertThat(userAccount01.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001");
				assertThat(userAccount01.getPostes()).isNull();
				
				userAccount01 = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(),new Properties().setFields(Arrays.asList(UserAccount.FIELD_POSTES,UserAccount.FIELD_PROFILES)));
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getPostes()).isNotNull();
				assertThat(userAccount01.getPostes().get()).isNotEmpty();
				assertThat(userAccount01.getPostes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
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
		__inject__(RoleCategoryBusiness.class).deleteAll();
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
		ScopeType locationType = new ScopeType().setCode("MINISTERE").setName("Ministère");
		__inject__(ScopeTypeBusiness.class).create(locationType);
		Scope location = new Scope().setIdentifier("21").setType(locationType);
		__inject__(ScopeBusiness.class).create(location);
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(RoleCategoryBusiness.class).create(roleCategory);
		Function roleFunction = new Function().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(FunctionBusiness.class).create(roleFunction);
		FunctionScope rolePoste = new FunctionScope().setFunction(roleFunction).setScope(location);
		__inject__(FunctionScopeBusiness.class).create(rolePoste);
		roleFunction = new Function().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(FunctionBusiness.class).create(roleFunction);
		rolePoste = new FunctionScope().setFunction(roleFunction).setScope(location);
		__inject__(FunctionScopeBusiness.class).create(rolePoste);
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p001").setName(__getRandomName__()));
		__inject__(ProfileBusiness.class).create(new Profile().setCode("p002").setName(__getRandomName__()));
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostes(__inject__(FunctionScopePersistence.class).readOneByBusinessIdentifier("CONTROLEUR_FINANCIER_MINISTERE_21"));
		userAccount.addProfiles(__inject__(ProfilePersistence.class).readOneByBusinessIdentifier("p001"));
		__inject__(UserAccountBusiness.class).create(userAccount);
		
		UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		UserRepresentation userRepresentation = userResource.toRepresentation();
		Map<String,List<String>> attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
				).hasSize(1);
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
				.add(UserAccount.FIELD_PROFILES,UserAccount.FIELD_POSTES)));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getPostes()).isNotNull();
		assertThat(userAccount.getPostes().get()).isNotEmpty();
		assertThat(userAccount.getPostes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		assertThat(userAccount.getProfiles()).isNotNull();
		assertThat(userAccount.getProfiles().get()).isNotEmpty();
		assertThat(userAccount.getProfiles().get().stream().map(Profile::getCode).collect(Collectors.toList())).contains("p001");
		
		userAccount.addRolePostes(__inject__(FunctionScopePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_21"));
		userAccount.addProfiles(__inject__(ProfilePersistence.class).readOneByBusinessIdentifier("p002"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(UserAccount.FIELD_POSTES));
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
				.add(UserAccount.FIELD_PROFILES,UserAccount.FIELD_POSTES)));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getPostes()).isNotNull();
		assertThat(userAccount.getPostes().get()).isNotEmpty();
		assertThat(userAccount.getPostes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
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
		
		location = new Scope().setIdentifier("18").setType(locationType);
		__inject__(ScopeBusiness.class).create(location);
		rolePoste = new FunctionScope().setFunction(roleFunction).setScope(location);
		__inject__(FunctionScopeBusiness.class).create(rolePoste);
		
		userAccount.addRolePostes(__inject__(FunctionScopePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_18"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(UserAccount.FIELD_POSTES));
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(__inject__(Strings.class)
				.add(UserAccount.FIELD_PROFILES,UserAccount.FIELD_POSTES)));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getPostes()).isNotNull();
		assertThat(userAccount.getPostes().get()).isNotEmpty();
		assertThat(userAccount.getPostes().get().stream().map(FunctionScope::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
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
		__inject__(RoleCategoryBusiness.class).deleteAll();
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
