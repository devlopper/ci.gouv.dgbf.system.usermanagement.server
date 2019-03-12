package ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak;

import org.cyk.utility.helper.Helper;
import org.keycloak.admin.client.Keycloak;

public interface KeycloakHelper extends Helper {

	KeycloakHelper setClient(Keycloak client);
	Keycloak getClient();
	
}
