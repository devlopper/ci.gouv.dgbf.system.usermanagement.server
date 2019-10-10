package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.helper.Helper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.ResourcePermissionResource;
import org.keycloak.admin.client.resource.ResourceResource;
import org.keycloak.admin.client.resource.RolePolicyResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;

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
	
	void createUserAccount(String firstName,String lastNames,String electronicMailAddress,String userName,String pass,Collection<String> rolesCodes,Map<String,List<String>> attributes);
	void createUserAccounts(Collection<UserAccount> userAccounts);
	void createUserAccounts(UserAccount...userAccounts);
	UserRepresentation getUserRepresentationByUserName(String userName);
	void deleteAllUsers(String userNameRegularExpression);
	
	void saveUserAccount(String identifier,String firstName,String lastNames,String electronicMailAddress,String userName,String pass,Collection<String> rolesCodes,Map<String,List<String>> attributes);
	void saveUserAccount(UserAccount userAccount);
	KeycloakHelper addUserAccountAttributeValue(String identifier,String attributeName,String attributeValue);
	KeycloakHelper addUserAccountAttributesValues(UserAccountFunctionScope userAccountFunctionScope);
	KeycloakHelper removeUserAccountAttributeValue(String identifier,String attributeName,String attributeValue);
	KeycloakHelper removeUserAccountAttributesValues(UserAccountFunctionScope userAccountFunctionScope);
	KeycloakHelper deleteUserAccount(String identifier);
	
	ClientResource getClientResource(String identifier);
	
	KeycloakHelper createClient(Service service);
	ClientResource getClient(Service service);
	KeycloakHelper deleteClient(Service service);
	
	KeycloakHelper createResource(Service service,Resource resource);
	ResourceResource getResource(Service service,Resource resource);
	KeycloakHelper deleteResource(Service service,Resource resource);
	
	KeycloakHelper createRolePolicy(Service service,Profile profile);
	RolePolicyResource getRolePolicy(Service service,Profile profile);
	KeycloakHelper deleteRolePolicy(Service service,Profile profile);
	
	KeycloakHelper createPermission(Profile profile,Service service,Resource resource);
	ResourcePermissionResource getPermission(Profile profile,Service service,Resource resource);
	KeycloakHelper deletePermission(Profile profile,Service service,Resource resource);
	
	//KeycloakHelper deleteRole(String code);
	
	KeycloakHelper load();
	KeycloakHelper loadFunctionType();
	KeycloakHelper loadFunction();
	KeycloakHelper loadFunctionScope();
	
	String ROLE_ATTRIBUTE_NAME = "nom";
	
}
