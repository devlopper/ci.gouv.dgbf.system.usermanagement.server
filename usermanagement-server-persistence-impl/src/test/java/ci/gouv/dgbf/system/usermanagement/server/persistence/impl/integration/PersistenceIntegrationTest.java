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
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountRolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocation;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocationType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;
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
		PosteLocationType posteLocationType = new PosteLocationType().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjects(posteLocationType).execute();
	}
	
	@Test
	public void create_posteLocation() throws Exception{
		PosteLocationType posteLocationType = new PosteLocationType().setCode(__getRandomCode__()).setName(__getRandomName__());
		PosteLocation posteLocation = new PosteLocation().setType(posteLocationType);
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(posteLocationType).addObjects(posteLocation).execute();
	}
	
	/* Role Category */
	
	@Test
	public void create_roleCategory() throws Exception{
		RoleCategory role = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjects(role).execute();
	}
	
	/* Role Function */
	
	@Test
	public void create_roleFunction() throws Exception{
		RoleFunction role = new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__());
		role.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(role.getCategory()).addObjects(role).execute();
	}
	
	/* Role Poste */
	
	@Test
	public void create_rolePoste() throws Exception{
		PosteLocationType locationType = new PosteLocationType().setCode(__getRandomCode__()).setName(__getRandomName__());
		PosteLocation location = new PosteLocation().setType(locationType);
		RolePoste role = new RolePoste().setLocation(location).setCode(__getRandomCode__()).setName(__getRandomName__());
		role.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__())));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(locationType,location,role.getFunction().getCategory(),role.getFunction()).addObjects(role).execute();
	}
	
	/* User Account*/
	
	@Test
	public void create_userAccount() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount()).addObjects(userAccount).execute();
	}
	
	@Test
	public void create_userAccountRolePoste_ministryIs21() throws Exception{
		PosteLocationType locationType = new PosteLocationType().setCode("MINISTERE").setName("Ministère");
		PosteLocation location = new PosteLocation().setIdentifier("21").setType(locationType);
		RolePoste rolePoste = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		rolePoste.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setLocation(location);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountRolePoste userAccountRolePoste = new UserAccountRolePoste();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setRolePoste(rolePoste);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(locationType,location,rolePoste.getFunction().getCategory(),rolePoste.getFunction()
				,rolePoste,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
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
		PosteLocationType locationType = new PosteLocationType().setCode("PROGRAMME").setName("Programme");
		PosteLocation location = new PosteLocation().setIdentifier("100").setType(locationType);
		RolePoste rolePoste = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		rolePoste.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setLocation(location);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountRolePoste userAccountRolePoste = new UserAccountRolePoste();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setRolePoste(rolePoste);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(locationType,location,rolePoste.getFunction().getCategory(),rolePoste.getFunction()
				,rolePoste,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
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
		PosteLocationType locationType = new PosteLocationType().setCode("UNITE_ADMINISTRATIVE").setName("Unité administrative");
		PosteLocation location = new PosteLocation().setIdentifier("200").setType(locationType);
		RolePoste rolePoste = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		rolePoste.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setLocation(location);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountRolePoste userAccountRolePoste = new UserAccountRolePoste();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setRolePoste(rolePoste);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(locationType,location,rolePoste.getFunction().getCategory(),rolePoste.getFunction()
				,rolePoste,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
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
