package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account;

import org.cyk.utility.server.persistence.jpa.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

public interface AccountPersistence extends PersistenceEntity<Account> {

	Account readByCodeByPass(String code,String pass);
	
}
