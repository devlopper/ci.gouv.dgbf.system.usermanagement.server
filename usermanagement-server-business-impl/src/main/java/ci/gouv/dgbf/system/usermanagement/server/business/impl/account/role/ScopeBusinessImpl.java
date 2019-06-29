package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

@ApplicationScoped
public class ScopeBusinessImpl extends AbstractBusinessEntityImpl<Scope, ScopePersistence> implements ScopeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Scope> __getPersistenceEntityClass__() {
		return Scope.class;
	}
	
}
