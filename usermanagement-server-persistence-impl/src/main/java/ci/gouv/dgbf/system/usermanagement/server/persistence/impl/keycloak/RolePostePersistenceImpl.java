package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractRolePostePersistenceImpl;

@Singleton @Keycloak
public class RolePostePersistenceImpl extends AbstractRolePostePersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Collection<RolePoste> read(Properties properties) {
		Collection<RolePoste> rolePostes = new ArrayList<>();
		Collection<RoleRepresentation> roleRepresentations = __inject__(KeycloakHelper.class).getRolesByProperty("type","POSTE");
		for(RoleRepresentation index : roleRepresentations)
			rolePostes.add(new RolePoste().setIdentifier(index.getId()).setRole(new Role().setCode(index.getName())));
		return rolePostes;
	}
	
}
