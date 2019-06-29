package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;

@ApplicationScoped
public class ScopeTypePersistenceImpl extends AbstractPersistenceEntityImpl<ScopeType> implements ScopeTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<ScopeType> getEntityClass() {
		return ScopeType.class;
	}
}
