package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@Singleton
public class RoleFunctionPersistenceImpl extends AbstractRoleFunctionPersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PersistenceServiceProvider<RoleFunction> create(RoleFunction roleFunction, Properties properties) {
		super.create(roleFunction, properties);
		__inject__(KeycloakHelper.class).createRole(roleFunction.getCode(), roleFunction.getName(), "FONCTION",roleFunction.getCategory().getCode());
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<RoleFunction> delete(RoleFunction roleFunction, Properties properties) {
		super.delete(roleFunction, properties);
		__inject__(KeycloakHelper.class).deleteRole(roleFunction.getCode());
		return this;
	}
}
