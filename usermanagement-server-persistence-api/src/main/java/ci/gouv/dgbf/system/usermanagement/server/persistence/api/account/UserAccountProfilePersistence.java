package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account;

import java.util.Collection;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;

public interface UserAccountProfilePersistence extends PersistenceEntity<UserAccountProfile> {

	Collection<UserAccountProfile> readByUserAccount(UserAccount userAccount);
	
}
