package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;

public abstract class AbstractUserAccountPersistenceImpl extends AbstractPersistenceEntityImpl<UserAccount> implements UserAccountPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<UserAccount> getEntityClass() {
		return UserAccount.class;
	}
	
}
