package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

@Singleton
public class AccountBusinessImpl extends AbstractBusinessEntityImpl<Account, AccountPersistence> implements AccountBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Account> __getPersistenceEntityClass__() {
		return Account.class;
	}
	
	@Override
	public Account findByCodeByPass(String code,String pass) {
		return getPersistence().readByCodeByPass(code, pass);
	}

	@Override
	public Account authenticate(String code, String pass) {
		Account account = findByCodeByPass(code, pass);
		if(account == null)
			throw new RuntimeException("Le nom d'utilisateur ou le mot de passe est inccorect.");
		return account;
	}
}
