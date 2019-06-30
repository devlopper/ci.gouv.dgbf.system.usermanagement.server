package ci.gouv.dgbf.system.usermanagement.server.business.api.account.role;

import java.util.Collection;

import org.cyk.utility.server.business.BusinessEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

public interface ProfileFunctionBusiness extends BusinessEntity<ProfileFunction> {

	Collection<ProfileFunction> findByFunctionCodes(Collection<String> functionCodes);
	Collection<ProfileFunction> findByFunctionCodes(String...functionCodes);
	Collection<ProfileFunction> findByFunctions(Collection<Function> functions);
	
	Collection<ProfileFunction> findByProfileCodes(Collection<String> profileCodes);
	Collection<ProfileFunction> findByProfileCodes(String...profileCodes);
	Collection<ProfileFunction> findByProfiles(Collection<Profile> profiles);
	
}
