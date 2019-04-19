package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;

public abstract class AbstractRoleCategoryPersistenceImpl extends AbstractPersistenceEntityImpl<RoleCategory> implements RoleCategoryPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RoleCategory> getEntityClass() {
		return RoleCategory.class;
	}
	
}
