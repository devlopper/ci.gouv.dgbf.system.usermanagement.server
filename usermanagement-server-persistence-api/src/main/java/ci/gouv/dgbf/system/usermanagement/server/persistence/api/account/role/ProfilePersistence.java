package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

public interface ProfilePersistence extends PersistenceEntity<Profile> {

	ProfilePersistence exportToKeycloak();
	
}
