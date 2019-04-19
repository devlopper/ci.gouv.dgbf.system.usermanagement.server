package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;

public abstract class AbstractRoleFunctionPersistenceImpl extends AbstractPersistenceEntityImpl<RoleFunction> implements RoleFunctionPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<RoleFunction> getEntityClass() {
		return RoleFunction.class;
	}
	
}
