package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.annotation.Default;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;
import org.keycloak.representations.idm.RoleRepresentation;
import static org.assertj.core.api.Assertions.assertThat;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.Producer;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak.Keycloak;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak.KeycloakHelper;

public class KeycloakIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void getRoles_entity_type() throws Exception{
		Class<?> singleSignOnQualifierClass = __inject__(Producer.class).getSingleSignOnQualifierClass();
		if(singleSignOnQualifierClass == null || Default.class.equals(singleSignOnQualifierClass)) {
			
		}else if(Keycloak.class.equals(singleSignOnQualifierClass)) {
			Properties properties = new Properties();
			Map<String,List<String>> attributes = new HashMap<>();
			attributes.put("entity", (List<String>) __inject__(CollectionHelper.class).instanciate(List.class,"type"));
			properties.setAttributes(attributes);
			Collection<RoleRepresentation> roleRepresentations = __inject__(KeycloakHelper.class).getRoles(properties);
			
			assertThat(roleRepresentations.stream().map(index -> index.getName()).collect(Collectors.toList())).containsExactly("ADMINISTRATIF","BUDGETAIRE");
		}
	}
}
