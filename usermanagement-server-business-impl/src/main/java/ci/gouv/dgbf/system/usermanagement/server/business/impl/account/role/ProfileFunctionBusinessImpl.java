package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

@ApplicationScoped
public class ProfileFunctionBusinessImpl extends AbstractBusinessEntityImpl<ProfileFunction, ProfileFunctionPersistence> implements ProfileFunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<ProfileFunction> findByFunctionCodes(Collection<String> functionCodes) {
		return getPersistence().readByFunctionCodes(functionCodes);
	}

	@Override
	public Collection<ProfileFunction> findByFunctionCodes(String... functionCodes) {
		return getPersistence().readByFunctionCodes(functionCodes);
	}

	@Override
	public Collection<ProfileFunction> findByFunctions(Collection<Function> functions) {
		return getPersistence().readByFunctions(functions);
	}

	@Override
	public Collection<ProfileFunction> findByProfileCodes(Collection<String> profileCodes) {
		return getPersistence().readByProfileCodes(profileCodes);
	}

	@Override
	public Collection<ProfileFunction> findByProfileCodes(String... profileCodes) {
		return getPersistence().readByProfileCodes(profileCodes);
	}

	@Override
	public Collection<ProfileFunction> findByProfiles(Collection<Profile> profiles) {
		return getPersistence().readByProfiles(profiles);
	}
	

}
