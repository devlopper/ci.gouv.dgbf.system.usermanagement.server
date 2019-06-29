package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;

@ApplicationScoped
public class ScopeTypeBusinessImpl extends AbstractBusinessEntityImpl<ScopeType, ScopeTypePersistence> implements ScopeTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ScopeType> __getPersistenceEntityClass__() {
		return ScopeType.class;
	}
	
}
