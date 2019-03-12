package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

public abstract class AbstractAccountBusinessImpl extends AbstractBusinessEntityImpl<Account, AccountPersistence> implements AccountBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Account> __getPersistenceEntityClass__() {
		return Account.class;
	}
	
}
