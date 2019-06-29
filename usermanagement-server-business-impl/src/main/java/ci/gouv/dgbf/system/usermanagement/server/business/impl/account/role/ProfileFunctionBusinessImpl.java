package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

@ApplicationScoped
public class ProfileFunctionBusinessImpl extends AbstractBusinessEntityImpl<ProfileFunction, ProfileFunctionPersistence> implements ProfileFunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ProfileFunction> __getPersistenceEntityClass__() {
		return ProfileFunction.class;
	}
	
}
