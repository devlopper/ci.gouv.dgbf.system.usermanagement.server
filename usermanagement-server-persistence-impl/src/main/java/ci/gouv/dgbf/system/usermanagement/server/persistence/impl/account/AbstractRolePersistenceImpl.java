package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;

public abstract class AbstractRolePersistenceImpl extends AbstractPersistenceEntityImpl<Role> implements RolePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<Role> getEntityClass() {
		return Role.class;
	}
	
}
