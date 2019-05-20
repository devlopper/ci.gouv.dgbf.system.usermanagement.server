package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

public class PersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Role Category */
	
	@Test
	public void create_roleCategory() throws Exception{
		RoleCategory role = new RoleCategory().setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjects(role).execute();
	}
	
	@Test
	public void read_roleCategory() throws Exception{
		__createCategoryFromKeycloak__();
		Collection<RoleCategory> roleCategories = __inject__(RoleCategoryPersistence.class).read();
		assertThat(roleCategories.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
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
		__createCategoryFromKeycloak__();
		__createFunctionFromKeycloak__();
		Collection<RoleFunction> roleFunctions = __inject__(RoleFunctionPersistence.class).read();
		assertThat(roleFunctions.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("AS","AC","CB","CF","DIRECTEUR");
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
		__createCategoryFromKeycloak__();
		__createFunctionFromKeycloak__();
		__createPosteFromKeycloak__();
		Collection<RolePoste> rolePostes = __inject__(RolePostePersistence.class).read();
		assertThat(rolePostes.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("AS_MIN_21");
		
		RolePoste asMin21 = __inject__(RolePostePersistence.class).readOneByBusinessIdentifier("AS_MIN_21");
		assertThat(asMin21).isNotNull();
		assertThat(asMin21.getFunction()).isNotNull();
		assertThat(asMin21.getFunction().getCode()).isEqualTo("AS");
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
	
	private void __createCategoryFromKeycloak__() throws Exception{
		userTransaction.begin();
		for(RoleRepresentation index : __inject__(KeycloakHelper.class).getRolesByProperty("type", "CATEGORIE")) {
			RoleCategory category = __inject__(RoleCategoryPersistence.class).readOneByBusinessIdentifier(index.getName());
			if(category == null) {
				category = __inject__(RoleCategory.class).setCode(index.getName()).setName(index.getAttributes().get("name").get(0));
				__inject__(RoleCategoryPersistence.class).create(category);
			}
		}
		userTransaction.commit();
	}
	
	private void __createFunctionFromKeycloak__() throws Exception{
		userTransaction.begin();
		for(RoleRepresentation index : __inject__(KeycloakHelper.class).getRolesByProperty("type", "FONCTION")) {
			RoleFunction function = __inject__(RoleFunctionPersistence.class).readOneByBusinessIdentifier(index.getName());
			if(function == null) {
				function = __inject__(RoleFunction.class).setCode(index.getName()).setName(index.getAttributes().get("name").get(0));
				for(RoleRepresentation indexParent : __inject__(KeycloakHelper.class).getRolesResource().get(index.getName()).getRoleComposites()) {
					RoleCategory category = __inject__(RoleCategoryPersistence.class).readOneByBusinessIdentifier(indexParent.getName());
					if(category != null) {
						function.setCategory(category);
						break;
					}
				}
				if(function.getCategory() != null) {
					__inject__(RoleFunctionPersistence.class).create(function);	
				}
			}
		}
		userTransaction.commit();
	}
	
	private void __createPosteFromKeycloak__() throws Exception{
		userTransaction.begin();
		for(RoleRepresentation index : __inject__(KeycloakHelper.class).getRolesByProperty("type", "POSTE")) {
			RolePoste poste = __inject__(RolePostePersistence.class).readOneByBusinessIdentifier(index.getName());
			if(poste == null) {
				poste = __inject__(RolePoste.class).setCode(index.getName()).setName(index.getAttributes().get("name").get(0));
				for(RoleRepresentation indexParent : __inject__(KeycloakHelper.class).getRolesResource().get(index.getName()).getRoleComposites()) {
					RoleFunction function = __inject__(RoleFunctionPersistence.class).readOneByBusinessIdentifier(indexParent.getName());
					if(function != null) {
						poste.setFunction(function);
						break;
					}
				}
				if(poste.getFunction()!=null)
					__inject__(RolePostePersistence.class).create(poste);
			}
		}
		userTransaction.commit();
	}
}
