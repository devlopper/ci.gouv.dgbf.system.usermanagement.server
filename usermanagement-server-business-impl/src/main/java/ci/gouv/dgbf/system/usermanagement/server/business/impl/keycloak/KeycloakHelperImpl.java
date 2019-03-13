package ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.helper.AbstractHelper;
import org.cyk.utility.system.SystemHelper;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;

@Singleton
public class KeycloakHelperImpl extends AbstractHelper implements KeycloakHelper,Serializable {
	private static final long serialVersionUID = 1L;

	private String realmName;
	private Keycloak client;
	
	@Override
	public Keycloak getClient() {
		if(client == null) {
			String url = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.server.url");
			realmName = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.realm.name");
			String clientIdentifier = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.client.identifier");
			String clientSecret = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.client.secret");
			String username = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.credential.username");
			String password = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.credential.password");
			
			System.out.println("KEYCLOAK CLIENT TO BE CREATED\nurl:"+url+"\nrealm:"+realmName+"\nclient identifier:"+clientIdentifier+"\nclient secret:"+clientSecret
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
	
}
