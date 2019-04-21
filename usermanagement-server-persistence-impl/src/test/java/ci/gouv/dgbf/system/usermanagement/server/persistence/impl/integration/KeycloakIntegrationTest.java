package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;

import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

public class KeycloakIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void getRoles_entity_type() throws Exception{
		assertThat(__inject__(KeycloakHelper.class).getRolesByProperty("type","CATEGORIE").stream().map(index -> index.getName())
				.collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
	
	/*@Test
	public void getClientAuthorizationScopes() throws Exception{
		Properties properties = new Properties();
		Map<String,List<String>> attributes = new HashMap<>();
		attributes.put("entity", (List<String>) __inject__(CollectionHelper.class).instanciate(List.class,"type"));
		properties.setAttributes(attributes);
		Collection<RoleRepresentation> roleRepresentations = __inject__(KeycloakHelper.class).getRoles(properties);
		__inject__(KeycloakHelper.class).getRealmResource().clients().findAll().get(0).getAuthorizationSettings().
	}*/
}
