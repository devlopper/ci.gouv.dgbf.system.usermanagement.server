package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
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
	
	@Test
	public void read_roleCategory() throws Exception{
		Collection<RoleCategory> roleCategories = __inject__(RoleCategoryPersistence.class).read();
		assertThat(roleCategories.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
	
	@Test
	public void count_roleCategory() throws Exception{
		Long count = __inject__(RoleCategoryPersistence.class).count();
		assertThat(count).isEqualTo(2);
	}
	
	/* Role Function */
	
	@Test
	public void create_roleFunction() throws Exception{
		RoleFunction role = new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__());
		role.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__()));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(role.getCategory()).addObjects(role).execute();
	}
	
	@Test
	public void read_roleFunction() throws Exception{
		Collection<RoleFunction> roleFunctions = __inject__(RoleFunctionPersistence.class).read();
		assertThat(roleFunctions.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ASSISTANT_SAISIE","ADMINISTRATEUR_CREDITS","CONTROLEUR_BUDGETAIRE"
				,"CONTROLEUR_FINANCIER","DIRECTEUR");
		RoleFunction administrateur = __inject__(RoleFunctionPersistence.class).readOneByBusinessIdentifier("ADMINISTRATEUR");
		assertThat(administrateur).isNotNull();
		assertThat(administrateur.getCategory()).isNotNull();
		assertThat(administrateur.getCategory().getCode()).isEqualTo("ADMINISTRATIF");
	}
	
	/* Role Poste */
	
	@Test
	public void create_rolePoste() throws Exception{
		RolePoste role = new RolePoste().setCode(__getRandomCode__()).setName(__getRandomName__());
		role.setFunction(new RoleFunction().setCode(__getRandomCode__()).setName(__getRandomName__())
				.setCategory(new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__())));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(role.getFunction().getCategory(),role.getFunction()).addObjects(role).execute();
	}
	
	@Test
	public void read_rolePoste() throws Exception{
		Collection<RolePoste> rolePostes = __inject__(RolePostePersistence.class).read();
		assertThat(rolePostes.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ASSISTANT_SAISIE_MINISTERE_21");
		
		RolePoste asMin21 = __inject__(RolePostePersistence.class).readOneByBusinessIdentifier("ASSISTANT_SAISIE_MINISTERE_21");
		assertThat(asMin21).isNotNull();
		assertThat(asMin21.getFunction()).isNotNull();
		assertThat(asMin21.getFunction().getCode()).isEqualTo("ASSISTANT_SAISIE");
	}
	
	/* User Account*/
	
	@Test
	public void create_userAccount() throws Exception{
		UserAccount userAccount = new UserAccount();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setElectronicMailAddress(__getRandomCode__()+"@mail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		//userAccount.addRolePostes(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier("AS_MIN_21"));
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(userAccount.getUser(),userAccount.getAccount()).addObjects(userAccount).setIsCatchThrowable(Boolean.FALSE).execute();
	}
	
	/**/
	
	@Test
	public void read_keycloak_roleAttribute() throws Exception{
		assertThat(__inject__(KeycloakHelper.class).getRolesByProperty("type","CATEGORIE").stream().map(index -> index.getName())
				.collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
	
	/**/
	
	
}
