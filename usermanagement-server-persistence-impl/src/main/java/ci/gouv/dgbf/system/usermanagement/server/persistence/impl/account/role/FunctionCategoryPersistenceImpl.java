package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class FunctionCategoryPersistenceImpl extends AbstractPersistenceEntityImpl<FunctionCategory> implements FunctionCategoryPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PersistenceServiceProvider<FunctionCategory> create(FunctionCategory functionCategory, Properties properties) {
		super.create(functionCategory, properties);
		__inject__(KeycloakHelper.class).createRole(functionCategory.getCode(), functionCategory.getName(), "CATEGORIE");
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<FunctionCategory> delete(FunctionCategory functionCategory, Properties properties) {
		super.delete(functionCategory, properties);
		__inject__(KeycloakHelper.class).deleteRole(functionCategory.getCode());
		return this;
	}
	
	@Override
	public Class<FunctionCategory> getEntityClass() {
		return FunctionCategory.class;
	}
}
