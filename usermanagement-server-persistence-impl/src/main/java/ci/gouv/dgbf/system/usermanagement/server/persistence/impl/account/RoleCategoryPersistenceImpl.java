package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@Singleton
public class RoleCategoryPersistenceImpl extends AbstractRoleCategoryPersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PersistenceServiceProvider<RoleCategory> create(RoleCategory roleCategory, Properties properties) {
		super.create(roleCategory, properties);
		__inject__(KeycloakHelper.class).createRole(roleCategory.getCode(), roleCategory.getName(), "CATEGORIE");
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<RoleCategory> delete(RoleCategory roleCategory, Properties properties) {
		super.delete(roleCategory, properties);
		__inject__(KeycloakHelper.class).deleteRole(roleCategory.getCode());
		return this;
	}
}
