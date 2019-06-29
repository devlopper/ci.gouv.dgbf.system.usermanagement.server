package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountFunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountFunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;

@ApplicationScoped
public class UserAccountFunctionScopeBusinessImpl extends AbstractBusinessEntityImpl<UserAccountFunctionScope, UserAccountFunctionScopePersistence> implements UserAccountFunctionScopeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<UserAccountFunctionScope> findByUserAccount(UserAccount userAccount) {
		return getPersistence().readByUserAccount(userAccount);
	}
	
	@Override
	protected Class<UserAccountFunctionScope> __getPersistenceEntityClass__() {
		return UserAccountFunctionScope.class;
	}
	
}
