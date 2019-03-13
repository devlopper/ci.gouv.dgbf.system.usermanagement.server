package ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak;

import java.util.Collection;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.helper.Helper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;

public interface KeycloakHelper extends Helper {

	KeycloakHelper setClient(Keycloak client);
	Keycloak getClient();
	
	RealmResource getRealmResource();
	RolesResource getRolesResource();
	UsersResource getUsersResource();
	Collection<RoleRepresentation> getRoles(Properties properties);
	Collection<RoleRepresentation> getRoles();
}
