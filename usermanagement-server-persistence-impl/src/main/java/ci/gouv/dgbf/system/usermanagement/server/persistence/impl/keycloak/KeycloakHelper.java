package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.util.Collection;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.helper.Helper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientsResource;
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
	ClientsResource getClientsResource();
	
	Collection<RoleRepresentation> getRoles(Properties properties);
	Collection<RoleRepresentation> getRoles();
	Collection<RoleRepresentation> getRolesByProperty(String name,String...values);
	
	KeycloakHelper createRole(String code,String name,String type,Collection<String> parentsCodes);
	KeycloakHelper createRole(String code,String name,String type,String...parentsCodes);
	KeycloakHelper deleteRole(String code);
	
	String createUserAccount(String firstName,String lastNames,String electronicMailAddress,String userName,String pass,Collection<String> rolesCodes);
	KeycloakHelper deleteUserAccount(String identifier);
	
	KeycloakHelper load();
	KeycloakHelper loadRoleCategory();
	KeycloakHelper loadRoleFunction();
	KeycloakHelper loadRolePoste();
}
