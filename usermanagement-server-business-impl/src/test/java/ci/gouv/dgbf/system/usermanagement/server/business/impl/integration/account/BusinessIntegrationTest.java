package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.system.node.SystemNodeServer;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PosteLocationBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PosteLocationTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocation;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocationType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileRoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;
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
		RoleFunction role = new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomCode__()).setCategory(roleCategory);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(roleCategory).addObjects(role).execute();
	}
	
	@Test
	public void create_rolePoste() throws Exception{
		PosteLocationType locationType = new PosteLocationType().setCode("MINISTERE").setName("Ministère");
		PosteLocation location = new PosteLocation().setIdentifier("21").setType(locationType);
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		RoleFunction roleFunction = new RoleFunction().setCode("ASSISTANT").setName("Assistant").setCategory(roleCategory);
		RolePoste role = new RolePoste().setFunction(roleFunction).setLocation(location);
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
		RoleFunction function = new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__());
		function.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()));
		Profile profile = new Profile();
		profile.setCode(__getRandomCode__()).setName(__getRandomName__());
		ProfileRoleFunction profileRoleFunction = new ProfileRoleFunction();
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
		
		PosteLocationType locationType = new PosteLocationType().setCode("MINISTERE").setName("Ministère");
		__inject__(PosteLocationTypeBusiness.class).create(locationType);
		PosteLocation location = new PosteLocation().setIdentifier("21").setType(locationType);
		__inject__(PosteLocationBusiness.class).create(location);
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(RoleCategoryBusiness.class).create(roleCategory);
		RoleFunction roleFunction = new RoleFunction().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(RoleFunctionBusiness.class).create(roleFunction);
		RolePoste rolePoste = new RolePoste().setFunction(roleFunction).setLocation(location);
		__inject__(RolePosteBusiness.class).create(rolePoste);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostes(rolePoste);
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
				assertThat(userAccount01.getPostes().get().stream().map(RolePoste::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
				
				UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
				UserRepresentation userRepresentation = userResource.toRepresentation();
				Map<String,List<String>> attributes = userRepresentation.getAttributes();
				assertThat(attributes).contains(
						new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
						).hasSize(1);
				
			}
		}).execute();
		
		__inject__(RolePosteBusiness.class).deleteAll();
		__inject__(RoleFunctionBusiness.class).deleteAll();
		__inject__(RoleCategoryBusiness.class).deleteAll();
		__inject__(PosteLocationBusiness.class).deleteAll();
		__inject__(PosteLocationTypeBusiness.class).deleteAll();
		
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
		PosteLocationType locationType = new PosteLocationType().setCode("MINISTERE").setName("Ministère");
		__inject__(PosteLocationTypeBusiness.class).create(locationType);
		PosteLocation location = new PosteLocation().setIdentifier("21").setType(locationType);
		__inject__(PosteLocationBusiness.class).create(location);
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(RoleCategoryBusiness.class).create(roleCategory);
		RoleFunction roleFunction = new RoleFunction().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(RoleFunctionBusiness.class).create(roleFunction);
		RolePoste rolePoste = new RolePoste().setFunction(roleFunction).setLocation(location);
		__inject__(RolePosteBusiness.class).create(rolePoste);
		roleFunction = new RoleFunction().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(RoleFunctionBusiness.class).create(roleFunction);
		rolePoste = new RolePoste().setFunction(roleFunction).setLocation(location);
		__inject__(RolePosteBusiness.class).create(rolePoste);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostes(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier("CONTROLEUR_FINANCIER_MINISTERE_21"));
		__inject__(UserAccountBusiness.class).create(userAccount);
		
		UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		UserRepresentation userRepresentation = userResource.toRepresentation();
		Map<String,List<String>> attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
				).hasSize(1);
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(UserAccount.FIELD_POSTES));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getPostes()).isNotNull();
		assertThat(userAccount.getPostes().get()).isNotEmpty();
		assertThat(userAccount.getPostes().get().stream().map(RolePoste::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		
		userAccount.addRolePostes(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_21"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(UserAccount.FIELD_POSTES));
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(UserAccount.FIELD_POSTES));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getPostes()).isNotNull();
		assertThat(userAccount.getPostes().get()).isNotEmpty();
		assertThat(userAccount.getPostes().get().stream().map(RolePoste::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21");
		
		userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		userRepresentation = userResource.toRepresentation();
		attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
				).hasSize(1);
		
		location = new PosteLocation().setIdentifier("18").setType(locationType);
		__inject__(PosteLocationBusiness.class).create(location);
		rolePoste = new RolePoste().setFunction(roleFunction).setLocation(location);
		__inject__(RolePosteBusiness.class).create(rolePoste);
		
		userAccount.addRolePostes(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_18"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(UserAccount.FIELD_POSTES));
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(UserAccount.FIELD_POSTES));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getPostes()).isNotNull();
		assertThat(userAccount.getPostes().get()).isNotEmpty();
		assertThat(userAccount.getPostes().get().stream().map(RolePoste::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21","ASSISTANT_SAISIE_MINISTERE_18");
		
		userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		userRepresentation = userResource.toRepresentation();
		attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("MINISTERE",(List<String>)__inject__(CollectionHelper.class).instanciate("21","18"))
				).hasSize(1);
		
		__inject__(UserAccountBusiness.class).delete(userAccount);
		__inject__(RolePosteBusiness.class).deleteAll();
		__inject__(RoleFunctionBusiness.class).deleteAll();
		__inject__(RoleCategoryBusiness.class).deleteAll();
		__inject__(PosteLocationBusiness.class).deleteAll();
		__inject__(PosteLocationTypeBusiness.class).deleteAll();
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
