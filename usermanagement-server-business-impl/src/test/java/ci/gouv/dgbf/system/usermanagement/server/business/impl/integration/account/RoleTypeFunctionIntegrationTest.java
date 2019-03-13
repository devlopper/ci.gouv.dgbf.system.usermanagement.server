package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.annotation.Default;
import org.cyk.utility.server.business.test.TestBusinessCreate;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.Producer;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak.Keycloak;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;

public class RoleTypeFunctionIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void createOneRoleType() throws Exception{
		Class<?> singleSignOnQualifierClass = __inject__(Producer.class).getSingleSignOnQualifierClass();
		if(singleSignOnQualifierClass == null || Default.class.equals(singleSignOnQualifierClass)) {
			RoleType roleType = new RoleType().setCode(__getRandomCode__()).setName(__getRandomCode__());
			__inject__(TestBusinessCreate.class).addObjects(roleType).execute();	
		}		
	}
	
	@Test
	public void findAllRoleType() throws Exception{
		Class<?> singleSignOnQualifierClass = __inject__(Producer.class).getSingleSignOnQualifierClass();
		if(singleSignOnQualifierClass == null || Default.class.equals(singleSignOnQualifierClass)) {
			
		}else if(Keycloak.class.equals(singleSignOnQualifierClass)) {
			Collection<RoleType> roleTypes = __inject__(RoleTypeBusiness.class).findMany();
			assertThat(roleTypes.stream().map(index -> index.getCode()).collect(Collectors.toList())).containsExactly("ADMINISTRATIF","BUDGETAIRE");
		}
	}
}
