package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;

@ApplicationScoped
public class UserAccountScopeBusinessImpl extends AbstractBusinessEntityImpl<UserAccountScope, UserAccountScopePersistence> implements UserAccountScopeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

}