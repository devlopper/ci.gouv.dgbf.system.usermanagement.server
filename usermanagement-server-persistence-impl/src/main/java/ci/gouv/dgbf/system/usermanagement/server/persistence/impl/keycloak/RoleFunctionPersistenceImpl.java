package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractRoleFunctionPersistenceImpl;

@Singleton @Keycloak
public class RoleFunctionPersistenceImpl extends AbstractRoleFunctionPersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Collection<RoleFunction> read(Properties properties) {
		Collection<RoleFunction> roleFunctions = new ArrayList<>();
		Collection<RoleRepresentation> roleRepresentations = __inject__(KeycloakHelper.class).getRolesByProperty("type","FONCTION");
		for(RoleRepresentation index : roleRepresentations)
			roleFunctions.add(new RoleFunction().setIdentifier(index.getId()).setRole(new Role().setCode(index.getName())));
		return roleFunctions;
	}
	
}
