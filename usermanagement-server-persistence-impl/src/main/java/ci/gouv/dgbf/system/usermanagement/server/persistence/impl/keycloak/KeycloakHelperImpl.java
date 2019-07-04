package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.UserTransaction;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.cyk.utility.__kernel__.object.__static__.identifiable.AbstractIdentified;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.configuration.ConstantParameterName;
import org.cyk.utility.helper.AbstractHelper;
import org.cyk.utility.string.StringHelper;
import org.cyk.utility.value.ValueHelper;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.ResourcePermissionResource;
import org.keycloak.admin.client.resource.ResourceResource;
import org.keycloak.admin.client.resource.RolePolicyResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.ResourcePermissionRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.RolePolicyRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;

@ApplicationScoped
public class KeycloakHelperImpl extends AbstractHelper implements KeycloakHelper,Serializable {
	private static final long serialVersionUID = 1L;

	private String realmName;
	private Keycloak client;
	
	@Override
	public Keycloak getClient() {
		if(client == null) {
			String url = __inject__(ValueHelper.class).returnOrThrowIfBlank("keycloak server url",ConstantParameterName.get("keycloak.server.url")); 
			realmName = __inject__(ValueHelper.class).returnOrThrowIfBlank("keycloak realm name",ConstantParameterName.get("keycloak.realm.name")); 
			String clientIdentifier = __inject__(ValueHelper.class).returnOrThrowIfBlank("keycloak client identifier",ConstantParameterName.get("keycloak.client.identifier")); 
			String clientSecret = __inject__(ValueHelper.class).returnOrThrowIfBlank("keycloak client secret",ConstantParameterName.get("keycloak.client.secret")); 
			String username = __inject__(ValueHelper.class).returnOrThrowIfBlank("keycloak credentials username",ConstantParameterName.get("keycloak.credential.username")); 
			String password = __inject__(ValueHelper.class).returnOrThrowIfBlank("keycloak credentials password",ConstantParameterName.get("keycloak.credential.password"));
			
			__logInfo__("KEYCLOAK CLIENT TO BE CREATED\nurl:"+url+"\nrealm:"+realmName+"\nclient identifier:"+clientIdentifier+"\nclient secret:"+clientSecret
					+"\nusername:"+username+"\npassword:"+password);
			
			client = KeycloakBuilder.builder().serverUrl(url).realm(realmName).grantType(OAuth2Constants.PASSWORD).clientId(clientIdentifier) //
					.clientSecret(clientSecret).username(username).password(password).build();
		}
		return client;
	}

	@Override
	public KeycloakHelper setClient(Keycloak client) {
		this.client = client;
		return this;
	}
	
	@Override
	public RealmResource getRealmResource() {
		return getClient().realm(realmName);
	}
	
	@Override
	public UsersResource getUsersResource() {
		return getRealmResource().users();
	}
	
	@Override
	public RolesResource getRolesResource() {
		return getRealmResource().roles();
	}
	
	@Override
	public ClientsResource getClientsResource() {
		return getRealmResource().clients();
	}
	
	@Override
	public Collection<RoleRepresentation> getRoles(Properties properties) {
		Collection<RoleRepresentation> roleRepresentations = new ArrayList<>();
		RolesResource rolesResource = getRolesResource();
		@SuppressWarnings("unchecked")
		Map<String,List<String>> attributes = properties == null ? null : (Map<String, List<String>>) properties.getAttributes();
		for(RoleRepresentation index : rolesResource.list()) {
			index = rolesResource.get(index.getName()).toRepresentation();
			Boolean add = Boolean.TRUE;
			if(attributes!=null && !attributes.isEmpty()) {
				add = Boolean.FALSE;
				if(index.getAttributes()!=null) {
					for(Map.Entry<String, List<String>> indexEntry : attributes.entrySet()) {
						if(index.getAttributes().containsKey(indexEntry.getKey())) {
							if(Boolean.TRUE.equals(__inject__(CollectionHelper.class).containsAll(index.getAttributes().get(indexEntry.getKey()), attributes.get(indexEntry.getKey())))){
								add = Boolean.TRUE;
								break;
							}
						}else {
							break;
						}
					}
				}
			}
			
			if(Boolean.TRUE.equals(add))
				roleRepresentations.add(index);
		}
		return roleRepresentations;
	}
	
	@Override
	public Collection<RoleRepresentation> getRoles() {
		return getRoles(null);
	}
	
	@Override
	public Collection<RoleRepresentation> getRolesByProperty(String name, String... values) {
		Properties keycloakProperties = new Properties();
		Map<String,List<String>> attributes = new HashMap<>();
		attributes.put(name, (List<String>) __inject__(CollectionHelper.class).instanciate(List.class,values));
		keycloakProperties.setAttributes(attributes);
		return __inject__(KeycloakHelper.class).getRoles(keycloakProperties);
	}

	/**/
	
	@Override
	public KeycloakHelper createRole(String code,String name, String type,Collection<String> parentsCodes) {
		RoleResource roleResource = null;
		RoleRepresentation roleRepresentation  = null;
		try {
			roleResource = getRolesResource().get(code);
			roleRepresentation = roleResource.toRepresentation();
		} catch (NotFoundException exception) {
			//if existing then log
			//__logSevere__("Role <<"+code+">> not found. "+exception);
		}/* catch (ProcessingException exception) {
			__logSevere__("Cannot create role <<"+code+">>. "+exception);
			return this;
		}*/
		if(roleRepresentation == null) {
			roleRepresentation = new RoleRepresentation();
			roleRepresentation.setName(code);
			roleRepresentation.setDescription(name);
			
			RolesResource rolesResource = __inject__(KeycloakHelper.class).getRolesResource();
			rolesResource.create(roleRepresentation);
			
			updateAttributes(code,name,type, rolesResource.get(code));
			saveRoleComposites(code, parentsCodes);	
		}
		return this;
	}
	
	@Override
	public KeycloakHelper createRole(String code, String name, String type, String... parentsCodes) {
		return createRole(code, name, type, __inject__(CollectionHelper.class).instanciate(parentsCodes));
	}
	
	@Override
	public KeycloakHelper deleteRole(String code) {
		try {
			getRolesResource().deleteRole(code);
		} catch (NotFoundException exception) {
			__logWarning__("Role <<"+code+">> not found. "+exception);
		}/* catch (ProcessingException exception) {
			__logSevere__("Cannot delete role <<"+code+">>. "+exception);
			return this;
		}*/
		return this;
	}
	
	private void updateAttributes(String code,String name,String type,RoleResource roleResource) {
		RoleRepresentation roleRepresentation = roleResource.toRepresentation();
		Map<String,List<String>> attributes = new LinkedHashMap<>();
		attributes.put(ROLE_ATTRIBUTE_NAME, Arrays.asList(name));
		if(__inject__(StringHelper.class).isNotBlank(type))
			attributes.put("type", Arrays.asList(type));
		roleRepresentation.setAttributes(attributes);
		roleResource.update(roleRepresentation);
	}
	
	private void saveRoleComposites(String code,Collection<String> parentsCodes) {
		if(Boolean.TRUE.equals(__inject__(CollectionHelper.class ).isNotEmpty(parentsCodes))) {
			RolesResource rolesResource = getRolesResource();
			List<RoleRepresentation> composites = new ArrayList<>();
			for(String indexParentCode : parentsCodes) {
				RoleResource roleResource;
				try {
					roleResource = rolesResource.get(indexParentCode);
					if(roleResource!=null)
						composites.add(roleResource.toRepresentation());
				} catch (NotFoundException exception) {
					
				}
			}
			if(__inject__(CollectionHelper.class).isNotEmpty(composites))
				rolesResource.get(code).addComposites(composites);	
		}
	}
	
	@Override
	public String saveUserAccount(String identifier,String firstName, String lastNames, String electronicMailAddress,String userName, String pass,Collection<String> rolesCodes,Map<String,List<String>> attributes) {
		UsersResource usersRessource = getUsersResource();
		UserResource userResource = null;
		if(__inject__(StringHelper.class).isNotBlank(identifier))
			try {
				userResource = usersRessource.get(identifier);
			} catch (NotFoundException exception) {
				__logSevere__("User account <<"+identifier+">> to save not found. "+exception);
			}/* catch (ProcessingException exception) {
				__logSevere__("Cannot save user account <<"+identifier+">>. "+exception);
				return null;
			}*/
		UserRepresentation userRepresentation = null;
		if(userResource!=null)
			//try {
				userRepresentation = userResource.toRepresentation();	
			/*} catch (ProcessingException exception) {
				__logSevere__("Cannot save user account <<"+identifier+">>. "+exception);
				return null;
			}*/
			
		if(userRepresentation == null) {
			userRepresentation = new UserRepresentation();
			userRepresentation.setEnabled(true);
		}else {
			
		}
		
		userRepresentation.setUsername(userName);
		userRepresentation.setFirstName(firstName);
		userRepresentation.setLastName(lastNames);
		userRepresentation.setEmail(electronicMailAddress);
		userRepresentation.setAttributes(attributes);
		
		if(__inject__(StringHelper.class).isBlank(identifier)) {
			Response response = usersRessource.create(userRepresentation);
			identifier = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");	
			userResource = usersRessource.get(identifier);
			
			CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
			credentialRepresentation.setTemporary(Boolean.TRUE);
			credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
			credentialRepresentation.setValue(__inject__(ValueHelper.class).defaultToIfNull(pass, "123"));
			userResource.resetPassword(credentialRepresentation);
		}
				
		if(__inject__(CollectionHelper.class).isNotEmpty(rolesCodes)) 
			for(String index : rolesCodes) {
				try {
					RoleRepresentation roleRepresentation = getRealmResource().roles().get(index).toRepresentation();
					userResource.roles().realmLevel().add(Arrays.asList(roleRepresentation));
				} catch (NotFoundException exception) {
					System.out.println("Saving user account. Role "+exception+" : "+index);
				}
			}
			
		return identifier;
	}
	
	@Override
	public String saveUserAccount(UserAccount userAccount) {
		Collection<String> rolesCodes = __inject__(CollectionHelper.class).isEmpty(userAccount.getProfiles()) ? null : userAccount.getProfiles().get()
				.stream().map(x -> x.getCode()).collect(Collectors.toList());
		
		String identifier = saveUserAccount(userAccount.getIdentifier(),userAccount.getUser().getFirstName(), userAccount.getUser().getLastNames(), userAccount.getUser().getElectronicMailAddress()
				, userAccount.getAccount().getIdentifier(),  userAccount.getAccount().getPass(),  rolesCodes,null);
		return identifier;
	}
	
	@Override
	public KeycloakHelper deleteUserAccount(String identifier) {
		try{
			getUsersResource().get(identifier).remove();
		} catch (NotFoundException exception) {
			__logSevere__("User account <<"+identifier+">> not found. "+exception);
		}/* catch (ProcessingException exception) {
			__logSevere__("Cannot delete user account <<"+identifier+">>. "+exception);
			return this;
		}*/
		return this;
	}

	@Override
	public KeycloakHelper addUserAccountAttributeValue(String identifier, String attributeName, String attributeValue) {
		if(Boolean.TRUE.equals(__inject__(StringHelper.class).isNotBlank(attributeName)) && Boolean.TRUE.equals(__inject__(StringHelper.class).isNotBlank(attributeValue))) {
			UserResource userResource = getUsersResource().get(identifier);
			UserRepresentation userRepresentation = userResource.toRepresentation();
			Map<String,List<String>> attributes = userRepresentation.getAttributes();
			if(attributes == null)
				userRepresentation.setAttributes(attributes = new LinkedHashMap<>());
			List<String> values = attributes.get(attributeName);
			if(values == null)
				attributes.put(attributeName, values = new ArrayList<>());
			if(!values.contains(attributeValue))
				values.add(attributeValue);
			userResource.update(userRepresentation);	
		}
		return this;
	}
	
	@Override
	public KeycloakHelper addUserAccountAttributesValues(UserAccountFunctionScope userAccountFunctionScope) {
		__addUserAccountAttributesValues__(userAccountFunctionScope, userAccountFunctionScope.getFunctionScope().getScope().getType().getCode(), userAccountFunctionScope.getFunctionScope().getScope());
		return this;
	}
	
	private void __addUserAccountAttributesValues__(UserAccountFunctionScope userAccountFunctionScope,String attributeName,AbstractIdentified<?> identified) {
		if(identified!=null && identified.getIdentifier()!=null)
			addUserAccountAttributeValue(userAccountFunctionScope.getUserAccount().getIdentifier(), attributeName, identified.getIdentifier().toString());
	}
	
	@Override
	public KeycloakHelper removeUserAccountAttributeValue(String identifier, String attributeName,String attributeValue) {
		if(Boolean.TRUE.equals(__inject__(StringHelper.class).isNotBlank(attributeName)) && Boolean.TRUE.equals(__inject__(StringHelper.class).isNotBlank(attributeValue))) {
			UserResource userResource = getUsersResource().get(identifier);
			UserRepresentation userRepresentation = userResource.toRepresentation();
			Map<String,List<String>> attributes = userRepresentation.getAttributes();
			if(attributes != null) {
				List<String> values = attributes.get(attributeName);
				if(values != null) {
					values.remove(attributeValue);
					userResource.update(userRepresentation);		
				}	
			}
		}
		return this;
	}
	
	@Override
	public KeycloakHelper removeUserAccountAttributesValues(UserAccountFunctionScope userAccountFunctionScope) {
		__removeUserAccountAttributesValues__(userAccountFunctionScope, userAccountFunctionScope.getFunctionScope().getScope().getType().getCode(), userAccountFunctionScope.getFunctionScope().getScope());
		return this;
	}
	
	private void __removeUserAccountAttributesValues__(UserAccountFunctionScope userAccountFunctionScope,String attributeName,AbstractIdentified<?> identified) {
		if(identified!=null && identified.getIdentifier()!=null)
			removeUserAccountAttributeValue(userAccountFunctionScope.getUserAccount().getIdentifier(), attributeName, identified.getIdentifier().toString());
	}
	
	/**/
	
	/*protected ClientResource __getClientResource__(Service service) {
		getClient().realm(realmName).clients().
	}
	*/
	
	@Override
	public ClientResource getClientResource(String identifier) {
		try {
			return getClient().realm(realmName).clients().get(identifier);
		} catch (Exception exception) {
			__logWarning__(String.format("Identifier <<%s>> not found. %s", identifier,exception.toString()));
		}
		return null;
	}
	
	@Override
	public KeycloakHelper createClient(Service service) {
		ClientRepresentation clientRepresentation = new ClientRepresentation();
		clientRepresentation.setId(service.getIdentifier());
		clientRepresentation.setRootUrl(service.getUrl());
		getClient().realm(realmName).clients().create(clientRepresentation);
		return this;
	}
	
	@Override
	public ClientResource getClient(Service service) {
		return getClientResource(service.getIdentifier());
	}
	
	@Override
	public KeycloakHelper deleteClient(Service service) {
		ClientResource clientResource = getClient(service);
		if(clientResource != null)
			clientResource.remove();
		return this;
	}
	
	@Override
	public KeycloakHelper createResource(Service service, Resource resource) {
		ClientResource clientResource = getClient(service);
		if(clientResource != null) {
			ResourceRepresentation resourceRepresentation = new ResourceRepresentation();
			resourceRepresentation.setId(service.getIdentifier()+resource.getIdentifier());
			resourceRepresentation.setName(resource.getName());
			Set<String> uris = new HashSet<>();
			uris.add(resource.getUrl());
			resourceRepresentation.setUris(uris);
			clientResource.authorization().resources().create(resourceRepresentation);	
		}
		return this;
	}
	
	@Override
	public ResourceResource getResource(Service service, Resource resource) {
		ClientResource clientResource = getClient(service);
		if(clientResource != null) {
			String identifier = service.getIdentifier()+resource.getIdentifier();
			try {
				ResourceResource resourceResource = clientResource.authorization().resources().resource(identifier);
				ResourceRepresentation resourceRepresentation = null;
				if(resourceResource != null)
					resourceRepresentation = resourceResource.toRepresentation();//TO be sure that it exist
				return resourceRepresentation == null ? null : resourceResource;
			} catch (Exception exception) {
				__logWarning__(String.format("Identifier <<%s>> not found. %s", identifier,exception.toString()));
			}	
		}
		return null;
	}
	
	@Override
	public KeycloakHelper deleteResource(Service service, Resource resource) {
		ResourceResource resourceResource = getResource(service, resource);
		if(resourceResource != null) {
			resourceResource.remove();	
		}
		return this;
	}
	
	@Override
	public KeycloakHelper createRolePolicy(Service service,Profile profile) {
		ClientResource clientResource = getClient().realm(realmName).clients().get(service.getIdentifier());
		RolePolicyRepresentation rolePolicyRepresentation = new RolePolicyRepresentation();
		rolePolicyRepresentation.setId(service.getIdentifier()+profile.getIdentifier());
		rolePolicyRepresentation.setName(profile.getCode());
		rolePolicyRepresentation.setDescription(profile.getName());
		rolePolicyRepresentation.addRole(profile.getCode(), Boolean.TRUE);
		clientResource.authorization().policies().role().create(rolePolicyRepresentation);
		return this;
	}
	
	@Override
	public RolePolicyResource getRolePolicy(Service service, Profile profile) {
		ClientResource clientResource = getClient(service);
		if(clientResource != null) {
			String identifier = service.getIdentifier()+profile.getIdentifier();
			try {
				return clientResource.authorization().policies().role().findById(identifier);
			} catch (Exception exception) {
				__logWarning__(String.format("Identifier <<%s>> not found. %s", identifier,exception.toString()));
			}	
		}
		return null;
	}
	
	@Override
	public KeycloakHelper deleteRolePolicy(Service service, Profile profile) {
		RolePolicyResource rolePolicyResource = getRolePolicy(service, profile);
		if(rolePolicyResource != null)
			rolePolicyResource.remove();
		return this;
	}
	
	@Override
	public KeycloakHelper createPermission(Profile profile,Service service,Resource resource) {
		ClientResource clientResource = getClient(service);
		if(clientResource != null) {
			ResourcePermissionRepresentation resourcePermissionRepresentation = new ResourcePermissionRepresentation();
			ResourceResource resourceResource = getResource(service, resource);
			if(resourceResource != null) {
				RolePolicyResource rolePolicyResource = getRolePolicy(service, profile);
				if(rolePolicyResource != null) {
					resourcePermissionRepresentation.setId(profile.getIdentifier()+service.getIdentifier()+resource.getIdentifier());
					resourcePermissionRepresentation.addResource(resourceResource.toRepresentation().getId());
					resourcePermissionRepresentation.addPolicy(rolePolicyResource.toRepresentation().getId());
					clientResource.authorization().permissions().resource().create(resourcePermissionRepresentation);
				}
			}	
		}
		return this;
	}
	
	@Override
	public ResourcePermissionResource getPermission(Profile profile, Service service, Resource resource) {
		ClientResource clientResource = getClient(service);
		if(clientResource != null) {
			String identifier = profile.getIdentifier()+service.getIdentifier()+resource.getIdentifier();
			try {
				ResourcePermissionResource resourcePermissionResource = clientResource.authorization().permissions().resource().findById(identifier);
				ResourcePermissionRepresentation resourcePermissionRepresentation = null;
				if(resourcePermissionResource != null)
					resourcePermissionRepresentation = resourcePermissionResource.toRepresentation();
				return resourcePermissionRepresentation == null ? null : resourcePermissionResource;
			} catch (Exception exception) {
				__logWarning__(String.format("Identifier <<%s>> not found. %s", identifier,exception.toString()));
			}	
		}
		return null;
	}
	
	@Override
	public KeycloakHelper deletePermission(Profile profile, Service service, Resource resource) {
		ResourcePermissionResource resourcePermissionsResource = getPermission(profile, service, resource);
		if(resourcePermissionsResource != null)
			resourcePermissionsResource.remove();
		return this;
	}
	
	/**/
	
	@Override
	public KeycloakHelper load() {
		loadRoleCategory();
		loadFunction();
		loadFunctionScope();
		__logInfo__("Data from keycloak loaded into database");
		return this;
	}
	
	@Override
	public KeycloakHelper loadRoleCategory() {
		UserTransaction userTransaction = __inject__(UserTransaction.class);
		try {
			userTransaction.begin();
			for(RoleRepresentation index : __inject__(KeycloakHelper.class).getRolesByProperty("type", "CATEGORIE")) {
				FunctionCategory category = __inject__(FunctionCategoryPersistence.class).readOneByBusinessIdentifier(index.getName());
				if(category == null) {
					category = __inject__(FunctionCategory.class).setCode(index.getName()).setName(index.getAttributes().get(ROLE_ATTRIBUTE_NAME).get(0));
					__inject__(FunctionCategoryPersistence.class).create(category);
				}
			}
			userTransaction.commit();	
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return this;
	}
	
	@Override
	public KeycloakHelper loadFunction() {
		UserTransaction userTransaction = __inject__(UserTransaction.class);
		try {
			userTransaction.begin();
			for(RoleRepresentation index : __inject__(KeycloakHelper.class).getRolesByProperty("type", "FONCTION")) {
				Function function = __inject__(FunctionPersistence.class).readOneByBusinessIdentifier(index.getName());
				if(function == null) {
					function = __inject__(Function.class).setCode(index.getName()).setName(index.getAttributes().get(ROLE_ATTRIBUTE_NAME).get(0));
					for(RoleRepresentation indexParent : __inject__(KeycloakHelper.class).getRolesResource().get(index.getName()).getRoleComposites()) {
						FunctionCategory category = __inject__(FunctionCategoryPersistence.class).readOneByBusinessIdentifier(indexParent.getName());
						if(category != null) {
							function.setCategory(category);
							break;
						}
					}
					if(function.getCategory() != null) {
						__inject__(FunctionPersistence.class).create(function);	
					}
				}
			}
			userTransaction.commit();	
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return this;
	}
	
	@Override
	public KeycloakHelper loadFunctionScope() {
		UserTransaction userTransaction = __inject__(UserTransaction.class);
		try {
			userTransaction.begin();
			for(RoleRepresentation index : __inject__(KeycloakHelper.class).getRolesByProperty("type", "???")) {
				FunctionScope functionScope = __inject__(FunctionScopePersistence.class).readOneByBusinessIdentifier(index.getName());
				if(functionScope == null) {
					functionScope = __inject__(FunctionScope.class).setCode(index.getName()).setName(index.getAttributes().get(ROLE_ATTRIBUTE_NAME).get(0));
					for(RoleRepresentation indexParent : __inject__(KeycloakHelper.class).getRolesResource().get(index.getName()).getRoleComposites()) {
						Function function = __inject__(FunctionPersistence.class).readOneByBusinessIdentifier(indexParent.getName());
						if(function != null) {
							functionScope.setFunction(function);
							break;
						}
					}
					if(functionScope.getFunction()!=null)
						__inject__(FunctionScopePersistence.class).create(functionScope);
				}
			}
			userTransaction.commit();
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return this;
	}
}
