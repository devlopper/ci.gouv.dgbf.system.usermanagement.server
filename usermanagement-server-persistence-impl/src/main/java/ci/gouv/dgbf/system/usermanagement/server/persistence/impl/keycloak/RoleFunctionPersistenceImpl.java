package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractRoleFunctionPersistenceImpl;

@Singleton @Keycloak
public class RoleFunctionPersistenceImpl extends AbstractRoleFunctionPersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Collection<RoleFunction> read(Properties properties) {
		return __instanciate__(RolePersistenceImpl.__instanciate__(__inject__(KeycloakHelper.class).getRolesByProperty("type","FONCTION")));
	}
	
	@Override
	public RoleFunction readOne(Object identifier, Properties properties) {
		return __instanciate__(__inject__(RolePersistence.class).readOne(identifier, properties));
	}
	
	public static RoleFunction __instanciate__(Role role) {
		return new RoleFunction().setIdentifier(role.getIdentifier()).setRole(role);
	}
	
	public static Collection<RoleFunction> __instanciate__(Collection<Role> roles) {
		return roles == null ? null : roles.stream().map(x -> __instanciate__(x)).collect(Collectors.toList());
	}
	
}
