package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class FunctionPersistenceImpl extends AbstractPersistenceEntityImpl<Function> implements FunctionPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PersistenceServiceProvider<Function> create(Function function, Properties properties) {
		super.create(function, properties);
		__inject__(KeycloakHelper.class).createRole(function.getCode(), function.getName(), "FONCTION",function.getCategory().getCode());
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<Function> delete(Function function, Properties properties) {
		super.delete(function, properties);
		__inject__(KeycloakHelper.class).deleteRole(function.getCode());
		return this;
	}
	
	@Override
	public Class<Function> getEntityClass() {
		return Function.class;
	}
}
