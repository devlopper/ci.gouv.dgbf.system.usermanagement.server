package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.MinistryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Ministry;
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
		Ministry ministry = new Ministry().setIdentifier("21");
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		RoleFunction roleFunction = new RoleFunction().setCode("ASSISTANT").setName("Assistant").setCategory(roleCategory);
		RolePoste role = new RolePoste().setFunction(roleFunction).setMinistry(ministry);
		__inject__(TestBusinessCreate.class).addObjectsToBeCreatedArray(ministry,roleCategory,roleFunction).addObjects(role).addTryEndRunnables(new Runnable() {
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
	public void create_userAccount() throws Exception{
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			startServersZookeeperAndKafka();
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		Ministry ministry = new Ministry().setIdentifier("21");
		__inject__(MinistryBusiness.class).create(ministry);
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(RoleCategoryBusiness.class).create(roleCategory);
		RoleFunction roleFunction = new RoleFunction().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(RoleFunctionBusiness.class).create(roleFunction);
		RolePoste rolePoste = new RolePoste().setFunction(roleFunction).setMinistry(ministry);
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
				assertThat(userAccount01.getRolePostes()).isNull();
				
				userAccount01 = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(),new Properties().setFields(UserAccount.FIELD_ROLE_POSTES));
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getRolePostes()).isNotNull();
				assertThat(userAccount01.getRolePostes().get()).isNotEmpty();
				assertThat(userAccount01.getRolePostes().get().stream().map(RolePoste::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
				
				UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
				UserRepresentation userRepresentation = userResource.toRepresentation();
				Map<String,List<String>> attributes = userRepresentation.getAttributes();
				assertThat(attributes).contains(
						new AbstractMap.SimpleEntry<String, List<String>>("ministere",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
						).hasSize(1);
				
			}
		}).execute();
		
		__inject__(RolePosteBusiness.class).deleteAll();
		__inject__(RoleFunctionBusiness.class).deleteAll();
		__inject__(RoleCategoryBusiness.class).deleteAll();
		__inject__(MinistryBusiness.class).deleteAll();
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}else {
			
		}
	}
	
	@Test
	public void update_userAccount() throws Exception{
		Ministry ministry = new Ministry().setIdentifier("21");
		__inject__(MinistryBusiness.class).create(ministry);
		RoleCategory roleCategory = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(RoleCategoryBusiness.class).create(roleCategory);
		RoleFunction roleFunction = new RoleFunction().setCode("CONTROLEUR_FINANCIER").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(RoleFunctionBusiness.class).create(roleFunction);
		RolePoste rolePoste = new RolePoste().setFunction(roleFunction).setMinistry(ministry);
		__inject__(RolePosteBusiness.class).create(rolePoste);
		roleFunction = new RoleFunction().setCode("ASSISTANT_SAISIE").setName(__getRandomName__()).setCategory(roleCategory);
		__inject__(RoleFunctionBusiness.class).create(roleFunction);
		rolePoste = new RolePoste().setFunction(roleFunction).setMinistry(ministry);
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
				new AbstractMap.SimpleEntry<String, List<String>>("ministere",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
				).hasSize(1);
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(UserAccount.FIELD_ROLE_POSTES));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getRolePostes()).isNotNull();
		assertThat(userAccount.getRolePostes().get()).isNotEmpty();
		assertThat(userAccount.getRolePostes().get().stream().map(RolePoste::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		
		userAccount.addRolePostes(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_21"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(UserAccount.FIELD_ROLE_POSTES));
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(UserAccount.FIELD_ROLE_POSTES));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getRolePostes()).isNotNull();
		assertThat(userAccount.getRolePostes().get()).isNotEmpty();
		assertThat(userAccount.getRolePostes().get().stream().map(RolePoste::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21");
		
		userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		userRepresentation = userResource.toRepresentation();
		attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("ministere",(List<String>)__inject__(CollectionHelper.class).instanciate("21"))
				).hasSize(1);
		
		ministry = new Ministry().setIdentifier("18");
		__inject__(MinistryBusiness.class).create(ministry);
		rolePoste = new RolePoste().setFunction(roleFunction).setMinistry(ministry);
		__inject__(RolePosteBusiness.class).create(rolePoste);
		
		userAccount.addRolePostes(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_18"));
		__inject__(UserAccountBusiness.class).update(userAccount,new Properties().setFields(UserAccount.FIELD_ROLE_POSTES));
		
		userAccount = __inject__(UserAccountBusiness.class).findOne(userAccount.getIdentifier(), new Properties().setFields(UserAccount.FIELD_ROLE_POSTES));
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getRolePostes()).isNotNull();
		assertThat(userAccount.getRolePostes().get()).isNotEmpty();
		assertThat(userAccount.getRolePostes().get().stream().map(RolePoste::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21","ASSISTANT_SAISIE_MINISTERE_18");
		
		userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
		userRepresentation = userResource.toRepresentation();
		attributes = userRepresentation.getAttributes();
		assertThat(attributes).contains(
				new AbstractMap.SimpleEntry<String, List<String>>("ministere",(List<String>)__inject__(CollectionHelper.class).instanciate("21","18"))
				).hasSize(1);
		
		__inject__(UserAccountBusiness.class).delete(userAccount);
		__inject__(RolePosteBusiness.class).deleteAll();
		__inject__(RoleFunctionBusiness.class).deleteAll();
		__inject__(RoleCategoryBusiness.class).deleteAll();
		__inject__(MinistryBusiness.class).deleteAll();
		
		
		
	}
}
