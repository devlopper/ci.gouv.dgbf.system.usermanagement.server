package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractRoleCategoryPersistenceImpl;

@Singleton @Keycloak
public class RoleCategoryPersistenceImpl extends AbstractRoleCategoryPersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Collection<RoleCategory> read(Properties properties) {
		return __instanciate__(RolePersistenceImpl.__instanciate__(__inject__(KeycloakHelper.class).getRolesByProperty("type","CATEGORIE")));
	}
	
	@Override
	public RoleCategory readOne(Object identifier, Properties properties) {
		return __instanciate__(__inject__(RolePersistence.class).readOne(identifier, properties));
	}
	
	public static RoleCategory __instanciate__(Role role) {
		return new RoleCategory().setIdentifier(role.getIdentifier());//.setRole(role);
	}
	
	public static Collection<RoleCategory> __instanciate__(Collection<Role> roles) {
		return roles == null ? null : roles.stream().map(x -> __instanciate__(x)).collect(Collectors.toList());
	}
	
	@Override
	public Long count(Properties arg0) {
		return null;
	}
}
