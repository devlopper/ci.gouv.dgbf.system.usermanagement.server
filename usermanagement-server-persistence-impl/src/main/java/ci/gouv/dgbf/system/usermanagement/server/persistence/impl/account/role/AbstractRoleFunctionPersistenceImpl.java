package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;

public abstract class AbstractRoleFunctionPersistenceImpl extends AbstractPersistenceEntityImpl<RoleFunction> implements RoleFunctionPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<RoleFunction> getEntityClass() {
		return RoleFunction.class;
	}
	
}
