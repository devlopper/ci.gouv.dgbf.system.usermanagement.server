package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.Keycloak;

public class RoleCategoryPersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	/*
	@Test
	public void createOneRole() throws Exception{
		RoleType type = new RoleType().setCode(__getRandomCode__()).setName(__getRandomName__());
		Role role = new Role().setType(type).setCode(__getRandomCode__()).setName(__getRandomName__());
		__inject__(TestPersistenceCreate.class).addObjectsToBeCreatedArray(type).addObjects(role).execute();
	}
	*/
	
	@Test
	public void read() throws Exception{
		DependencyInjection.setQualifierClass(RoleCategoryPersistence.class, Keycloak.Class.class);
		Collection<RoleCategory> roleCategories = __inject__(RoleCategoryPersistence.class).read();
		assertThat(roleCategories.stream().map(x -> x.getRole().getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
}
