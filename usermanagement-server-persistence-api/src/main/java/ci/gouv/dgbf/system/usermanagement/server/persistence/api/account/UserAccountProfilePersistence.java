package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account;

import java.util.Collection;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

public interface UserAccountProfilePersistence extends PersistenceEntity<UserAccountProfile> {

	Collection<UserAccountProfile> readByUserAccount(UserAccount userAccount);
	
	Collection<UserAccountProfile> readByProfilesCodes(Collection<String> profileCodes);
	Collection<UserAccountProfile> readByProfilesCodes(String...profileCodes);
	Collection<UserAccountProfile> readByProfiles(Collection<Profile> profiles);
	Collection<UserAccountProfile> readByProfiles(Profile...profiles);
}
