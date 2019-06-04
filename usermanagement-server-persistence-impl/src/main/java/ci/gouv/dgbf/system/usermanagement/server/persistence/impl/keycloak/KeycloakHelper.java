package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.helper.Helper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountRolePoste;

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
	
	String saveUserAccount(String firstName,String lastNames,String electronicMailAddress,String userName,String pass,Collection<String> rolesCodes,Map<String,List<String>> attributes);
	KeycloakHelper addUserAccountAttributeValue(String identifier,String attributeName,String attributeValue);
	KeycloakHelper addUserAccountAttributesValues(UserAccountRolePoste userAccountRolePoste);
	KeycloakHelper removeUserAccountAttributeValue(String identifier,String attributeName,String attributeValue);
	KeycloakHelper removeUserAccountAttributesValues(UserAccountRolePoste userAccountRolePoste);
	KeycloakHelper deleteUserAccount(String identifier);
	
	KeycloakHelper load();
	KeycloakHelper loadRoleCategory();
	KeycloakHelper loadRoleFunction();
	KeycloakHelper loadRolePoste();
	
	String ROLE_ATTRIBUTE_NAME = "nom";
	String USER_ACCOUNT_ATTRIBUTE_MINISTRY = "ministere";
	String USER_ACCOUNT_ATTRIBUTE_PROGRAM = "programme";
	String USER_ACCOUNT_ATTRIBUTE_ADMINISTRATIVE_UNIT = "unite_administrative";
}
