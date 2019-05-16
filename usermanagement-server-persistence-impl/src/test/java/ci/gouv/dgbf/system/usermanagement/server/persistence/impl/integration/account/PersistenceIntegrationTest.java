package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.ServicePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.Keycloak;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

public class PersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Role Type*/
	
	@Test
	public void roleType_create() throws Exception{
		RoleType roleType = new RoleType().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjects(roleType).execute();
	}
	
	/* Role */
	
	@Test
	public void role_create() throws Exception{
		RoleType type = new RoleType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Role role = new Role().setType(type).setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(type).addObjects(role).execute();
	}
	
	@Test
	public void role_read() throws Exception{
		Collection<Role> roles = __inject__(RolePersistence.class).read();
		assertThat(roles.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE","AS","RBOP","DIRECTEUR","AS_MIN_21");
	}
	
	@Test
	public void role_readByBusinessIdentifier() throws Exception{
		Role role = __inject__(RolePersistence.class).readOne("ADMINISTRATIF", new Properties());
		assertThat(role.getCode()).isEqualTo("ADMINISTRATIF");
	}
	
	/* Role Category */
	
	@Test
	public void roleCategory_read() throws Exception{
		DependencyInjection.setQualifierClass(RoleCategoryPersistence.class, Keycloak.Class.class);
		Collection<RoleCategory> roleCategories = __inject__(RoleCategoryPersistence.class).read();
		assertThat(roleCategories.stream().map(x -> x.getRole().getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
	
	/* Role Function */
	
	@Test
	public void roleFunction_read() throws Exception{
		DependencyInjection.setQualifierClass(RoleFunctionPersistence.class, Keycloak.Class.class);
		Collection<RoleFunction> roleFunctions = __inject__(RoleFunctionPersistence.class).read();
		assertThat(roleFunctions.stream().map(x -> x.getRole().getCode()).collect(Collectors.toList())).contains("RP","RBOP","AS","AC","CB","CF","DIRECTEUR");
	}
	
	/* Role Poste */
	
	@Test
	public void rolePoste_read() throws Exception{
		DependencyInjection.setQualifierClass(RolePostePersistence.class, Keycloak.Class.class);
		Collection<RolePoste> rolePostes = __inject__(RolePostePersistence.class).read();
		assertThat(rolePostes.stream().map(x -> x.getRole().getCode()).collect(Collectors.toList())).contains("AS_MIN_21");
	}
	
	/* Service */
	
	@Test
	public void service_read() throws Exception{
		Collection<Service> services = __inject__(ServicePersistence.class).read();
		assertThat(services.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("PLAN_ENGAGEMENT","AUTHENTIFICATION");
	}
	
	@Test
	public void service_readByBusinessIdentifier() throws Exception{
		Service service = __inject__(ServicePersistence.class).readOneByBusinessIdentifier("PLAN_ENGAGEMENT");
		assertThat(service.getCode()).isEqualTo("PLAN_ENGAGEMENT");
	}
	
	/* User Account*/
	
	@Test
	public void userAccount_create() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomCode__()+"@mail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRoles(__inject__(RolePersistence.class).readOneByBusinessIdentifier("CE"));
		__inject__(TestPersistenceCreate.class).addObjects(userAccount).setIsCatchThrowable(Boolean.FALSE).execute();
	}
	
	/**/
	
	@Test
	public void keycloak_role_readAttribute() throws Exception{
		assertThat(__inject__(KeycloakHelper.class).getRolesByProperty("type","CATEGORIE").stream().map(index -> index.getName())
				.collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
}
