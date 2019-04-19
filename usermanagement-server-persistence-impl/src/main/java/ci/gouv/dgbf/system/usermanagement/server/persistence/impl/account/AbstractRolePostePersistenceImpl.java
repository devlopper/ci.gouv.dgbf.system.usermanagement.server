package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;

public abstract class AbstractRolePostePersistenceImpl extends AbstractPersistenceEntityImpl<RolePoste> implements RolePostePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<RolePoste> getEntityClass() {
		return RolePoste.class;
	}
	
}
