package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;

public interface UserPersistence extends PersistenceEntity<User> {

	UserPersistence setFunctions(User user);
	
}
