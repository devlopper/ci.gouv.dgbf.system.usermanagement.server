package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;

public abstract class AbstractRoleBusinessImpl extends AbstractBusinessEntityImpl<Role, RolePersistence> implements RoleBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Role> __getPersistenceEntityClass__() {
		return Role.class;
	}
	
}
