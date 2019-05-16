package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractRolePersistenceImpl;

@Singleton @Keycloak
public class RolePersistenceImpl extends AbstractRolePersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public PersistenceServiceProvider<Role> create(Role role, Properties properties) {
		RoleRepresentation roleRepresentation = new RoleRepresentation();
		roleRepresentation.setName(role.getCode());
		roleRepresentation.setDescription(role.getDescription());
		
		RolesResource rolesResource = __inject__(KeycloakHelper.class).getRolesResource();
		rolesResource.create(roleRepresentation);
		
		updateAttributes(role, rolesResource.get(role.getCode()));
		return this;
	}
	
	private void updateAttributes(Role role,RoleResource roleResource) {
		RoleRepresentation roleRepresentation = roleResource.toRepresentation();
		Map<String,List<String>> attributes = new LinkedHashMap<>();
		attributes.put("name", Arrays.asList(role.getName()));
		if(role.getType() != null)
			attributes.put("type", Arrays.asList(role.getType().getCode()));
		roleRepresentation.setAttributes(attributes);
		roleResource.update(roleRepresentation);
	}
	
	@Override
	public Collection<Role> read(Properties properties) {
		Collection<Role> roles = new ArrayList<>();
		Collection<RoleRepresentation> roleRepresentations = __inject__(KeycloakHelper.class).getRoles();
		for(RoleRepresentation index : roleRepresentations)
			roles.add(__instanciate__(index));
		return roles;
	}
	
	@Override
	public Role readOne(Object identifier, Properties properties) {
		//we do not know how to find role by system identifier. So we will only find it by business identifier
		RoleRepresentation roleRepresentation = __inject__(KeycloakHelper.class).getRolesResource().get((String)identifier).toRepresentation();
		return __instanciate__(roleRepresentation);
	}
	
	public static Role __instanciate__(RoleRepresentation roleRepresentation) {
		//we can only find role by name so our identifier will be the name for now
		return new Role()
				.setIdentifier(roleRepresentation.getName())
				.setCode(roleRepresentation.getName())
				.setName(DependencyInjection.inject(CollectionHelper.class).getFirst(roleRepresentation.getAttributes().get("name")))
				;
	}
	
	public static Collection<Role> __instanciate__(Collection<RoleRepresentation> roleRepresentations) {
		return roleRepresentations == null ? null : roleRepresentations.stream().map(x -> __instanciate__(x)).collect(Collectors.toList());
	}
	
	@Override
	public PersistenceServiceProvider<Role> update(Role role, Properties properties) {
		RolesResource rolesResource = __inject__(KeycloakHelper.class).getRolesResource();
		try{
			updateAttributes(role, rolesResource.get(role.getCode()));
		}catch(NotFoundException exception) {
			
		}
		return this;
	}

	@Override
	public PersistenceServiceProvider<Role> delete(Role role, Properties properties) {
		try{
			__inject__(KeycloakHelper.class).getRolesResource().deleteRole(role.getCode());
		}catch(NotFoundException exception) {
			
		}
		return this;
	}
	
	@Override
	public Long count(Properties arg0) {
		return null;
	}
}
