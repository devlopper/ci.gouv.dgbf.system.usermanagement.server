package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import java.util.Collection;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileServiceResource;

public interface ProfileServiceResourcePersistence extends PersistenceEntity<ProfileServiceResource> {

	Collection<ProfileServiceResource> readByProfilesCodes(Collection<String> profilesCodes);
	Collection<ProfileServiceResource> readByProfilesCodes(String...profilesCodes);
	Collection<ProfileServiceResource> readByProfiles(Collection<Profile> profiles);
	Collection<ProfileServiceResource> readByProfiles(Profile...profiles);
}
