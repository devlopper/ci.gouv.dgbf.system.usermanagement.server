package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;

import org.cyk.utility.__kernel__.properties.Properties;
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
		attributes.put("type", Arrays.asList(role.getType().getCode()));
		roleRepresentation.setAttributes(attributes);
		roleResource.update(roleRepresentation);
	}
	
	@Override
	public Collection<Role> read(Properties properties) {
		Collection<Role> roles = new ArrayList<>();
		Collection<RoleRepresentation> roleRepresentations = __inject__(KeycloakHelper.class).getRoles();
		for(RoleRepresentation index : roleRepresentations)
			roles.add(new Role().setCode(index.getName()));
		return roles;
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
}