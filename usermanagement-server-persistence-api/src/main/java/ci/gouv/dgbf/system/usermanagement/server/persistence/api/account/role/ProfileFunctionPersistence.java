package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import java.util.Collection;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

public interface ProfileFunctionPersistence extends PersistenceEntity<ProfileFunction> {

	Collection<ProfileFunction> readByFunctionCodes(Collection<String> functionCodes);
	Collection<ProfileFunction> readByFunctionCodes(String...functionCodes);
	Collection<ProfileFunction> readByFunctions(Collection<Function> functions);
	
	Collection<ProfileFunction> readByProfileCodes(Collection<String> profileCodes);
	Collection<ProfileFunction> readByProfileCodes(String...profileCodes);
	Collection<ProfileFunction> readByProfiles(Collection<Profile> profiles);
	
}
