package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountRolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Ministry;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;
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
		RolePoste role = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		role.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__())));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(role.getFunction().getCategory(),role.getFunction()).addObjects(role).execute();
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
		Ministry ministry = new Ministry().setIdentifier("21");
		RolePoste rolePoste = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		rolePoste.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setMinistry(ministry);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountRolePoste userAccountRolePoste = new UserAccountRolePoste();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setRolePoste(rolePoste);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(ministry,rolePoste.getFunction().getCategory(),rolePoste.getFunction()
				,rolePoste,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
			.setIsCatchThrowable(Boolean.FALSE).addTryEndRunnables(new Runnable() {
				@Override
				public void run() {
					UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
					UserRepresentation userRepresentation = userResource.toRepresentation();
					Map<String,List<String>> attributes = userRepresentation.getAttributes();
					assertThat(attributes).containsExactly(new AbstractMap.SimpleEntry<String, List<String>>("ministere"
							,(List<String>)__inject__(CollectionHelper.class).instanciate("21")));
				}
			}).execute();
	}
	
	@Test
	public void create_userAccountRolePoste_programIs100() throws Exception{
		Program program = new Program().setIdentifier("100");
		RolePoste rolePoste = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		rolePoste.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setProgram(program);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountRolePoste userAccountRolePoste = new UserAccountRolePoste();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setRolePoste(rolePoste);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(program,rolePoste.getFunction().getCategory(),rolePoste.getFunction()
				,rolePoste,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
			.setIsCatchThrowable(Boolean.FALSE).addTryEndRunnables(new Runnable() {
				@Override
				public void run() {
					UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
					UserRepresentation userRepresentation = userResource.toRepresentation();
					Map<String,List<String>> attributes = userRepresentation.getAttributes();
					assertThat(attributes).containsExactly(new AbstractMap.SimpleEntry<String, List<String>>("programme"
							,(List<String>)__inject__(CollectionHelper.class).instanciate("100")));
				}
			}).execute();
	}
	
	@Test
	public void create_userAccountRolePoste_administrativeUnitIs200() throws Exception{
		AdministrativeUnit administrativeUnit = new AdministrativeUnit().setIdentifier("200");
		RolePoste rolePoste = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		rolePoste.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setAdministrativeUnit(administrativeUnit);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountRolePoste userAccountRolePoste = new UserAccountRolePoste();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setRolePoste(rolePoste);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(administrativeUnit,rolePoste.getFunction().getCategory(),rolePoste.getFunction()
				,rolePoste,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
			.setIsCatchThrowable(Boolean.FALSE).addTryEndRunnables(new Runnable() {
				@Override
				public void run() {
					UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
					UserRepresentation userRepresentation = userResource.toRepresentation();
					Map<String,List<String>> attributes = userRepresentation.getAttributes();
					assertThat(attributes).containsExactly(new AbstractMap.SimpleEntry<String, List<String>>("unite_administrative"
							,(List<String>)__inject__(CollectionHelper.class).instanciate("200")));
				}
			}).execute();
	}
	
	@Test
	public void create_userAccountRolePoste_ministry15_administrativeUnitIs200() throws Exception{
		Ministry ministry = new Ministry().setIdentifier("15");
		AdministrativeUnit administrativeUnit = new AdministrativeUnit().setIdentifier("200");
		RolePoste rolePoste = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		rolePoste.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()))).setMinistry(ministry).setAdministrativeUnit(administrativeUnit);
		
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomElectronicMailAddress__());
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		UserAccountRolePoste userAccountRolePoste = new UserAccountRolePoste();
		userAccountRolePoste.setUserAccount(userAccount);
		userAccountRolePoste.setRolePoste(rolePoste);
		
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(ministry,administrativeUnit,rolePoste.getFunction().getCategory(),rolePoste.getFunction()
				,rolePoste,userAccount.getUser(),userAccount.getAccount(),userAccount).addObjects(userAccountRolePoste)
			.setIsCatchThrowable(Boolean.FALSE).addTryEndRunnables(new Runnable() {
				@Override
				public void run() {
					UserResource userResource = __inject__(KeycloakHelper.class).getUsersResource().get(userAccount.getIdentifier());
					UserRepresentation userRepresentation = userResource.toRepresentation();
					Map<String,List<String>> attributes = userRepresentation.getAttributes();
					assertThat(attributes).contains(
							new AbstractMap.SimpleEntry<String, List<String>>("ministere",(List<String>)__inject__(CollectionHelper.class).instanciate("15"))
							,new AbstractMap.SimpleEntry<String, List<String>>("unite_administrative",(List<String>)__inject__(CollectionHelper.class).instanciate("200"))
							).hasSize(2);
				}
			}).execute();
	}
	
	/**/
	
}
