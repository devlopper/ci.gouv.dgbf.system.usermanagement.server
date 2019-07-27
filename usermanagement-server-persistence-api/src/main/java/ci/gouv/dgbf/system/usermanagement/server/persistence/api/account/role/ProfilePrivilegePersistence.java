package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import java.util.Collection;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;

public interface ProfilePrivilegePersistence extends PersistenceEntity<ProfilePrivilege> {

	Collection<ProfilePrivilege> readByProfileCodes(Collection<String> profileCodes);
	Collection<ProfilePrivilege> readByProfileCodes(String...profileCodes);
	Collection<ProfilePrivilege> readByProfiles(Collection<Profile> profiles);
	
}
