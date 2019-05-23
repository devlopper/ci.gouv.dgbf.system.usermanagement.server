package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;

public abstract class AbstractRoleCategoryPersistenceImpl extends AbstractPersistenceEntityImpl<RoleCategory> implements RoleCategoryPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RoleCategory> getEntityClass() {
		return RoleCategory.class;
	}
	
}
