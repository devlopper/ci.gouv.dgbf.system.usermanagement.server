package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import java.util.ArrayList;
import java.util.Collection;

import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak.KeycloakHelper;

public class PopulateKeycloakIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void populateRoles() throws Exception{
		Collection<String> roleCodes = new ArrayList<>();
		roleCodes.add("BUDGETAIRE001");
		
		RolesResource rolesResource = __inject__(KeycloakHelper.class).getRolesResource();
		for(String index : roleCodes) {
			RoleRepresentation roleRepresentation = new RoleRepresentation();
			roleRepresentation.setName(index);
			System.out.print("Creating role "+index+"... ");
			rolesResource.create(roleRepresentation);
			System.out.println("OK");
		}
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
