package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractRoleCategoryPersistenceImpl;

@Singleton @Keycloak
public class RoleCategoryPersistenceImpl extends AbstractRoleCategoryPersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Collection<RoleCategory> read(Properties properties) {
		Collection<RoleCategory> roleCategories = new ArrayList<>();
		Collection<RoleRepresentation> roleRepresentations = __inject__(KeycloakHelper.class).getRolesByProperty("type","CATEGORIE");
		for(RoleRepresentation index : roleRepresentations)
			roleCategories.add(new RoleCategory().setIdentifier(index.getId()).setRole(new Role().setCode(index.getName())));
		return roleCategories;
	}
	
}
