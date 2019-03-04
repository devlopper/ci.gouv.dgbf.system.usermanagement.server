package ci.gouv.dgbf.system.usermanagement.server.business.api.account;

import org.cyk.utility.server.business.BusinessEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

public interface AccountBusiness extends BusinessEntity<Account> {

	Account findByCodeByPass(String code,String pass);
	
	/**
	 * Authenticate a code. If code is known from the system then all related data like profile,privilege and so on are loaded
	 * @param code
	 * @param pass
	 * @return
	 */
	Account authenticate(String code,String pass);
}
