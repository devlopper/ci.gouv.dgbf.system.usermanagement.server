package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account;

import java.util.Collection;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;

public interface UserAccountScopePersistence extends PersistenceEntity<UserAccountScope> {

	Collection<UserAccountScope> readByUserAccountsIdentifiers(Collection<String> userAccountsIdentifiers,Properties properties);
	Collection<UserAccountScope> readByUserAccountsIdentifiers(Collection<String> userAccountsIdentifiers);
	Collection<UserAccountScope> readByUserAccountsIdentifiers(Properties properties,String...userAccountsIdentifiers);
	Collection<UserAccountScope> readByUserAccountsIdentifiers(String...userAccountsIdentifiers);
	
	Collection<UserAccountScope> readByUserAccounts(Collection<UserAccount> userAccounts,Properties properties);
	Collection<UserAccountScope> readByUserAccounts(Collection<UserAccount> userAccounts);
	Collection<UserAccountScope> readByUserAccounts(Properties properties,UserAccount...userAccounts);
	Collection<UserAccountScope> readByUserAccounts(UserAccount...userAccounts);
}
