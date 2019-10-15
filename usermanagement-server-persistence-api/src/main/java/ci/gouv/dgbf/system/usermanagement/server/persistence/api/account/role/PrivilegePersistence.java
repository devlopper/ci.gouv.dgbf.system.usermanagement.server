package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import java.util.Collection;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.jpa.hierarchy.PersistenceIdentifiedByStringAndCoded;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;

public interface PrivilegePersistence extends PersistenceIdentifiedByStringAndCoded<Privilege> {

	Collection<Privilege> readByUserAccountsIdentifiers(Collection<String> userAccountsIdentifiers,Properties properties);
	Collection<Privilege> readByUserAccountsIdentifiers(Collection<String> userAccountsIdentifiers);
	Collection<Privilege> readByUserAccountsIdentifiers(Properties properties,String...userAccountsIdentifiers);
	Collection<Privilege> readByUserAccountsIdentifiers(String...userAccountsIdentifiers);
	
	Collection<Privilege> readByUserAccounts(Collection<UserAccount> userAccounts,Properties properties);
	Collection<Privilege> readByUserAccounts(Collection<UserAccount> userAccounts);
	Collection<Privilege> readByUserAccounts(Properties properties,UserAccount...userAccounts);
	Collection<Privilege> readByUserAccounts(UserAccount...userAccounts);
	
	/**/
	
	String READ_BY_PROFILES_IDENTIFIERS = "READ_BY_PROFILES_IDENTIFIERS";
	
}
