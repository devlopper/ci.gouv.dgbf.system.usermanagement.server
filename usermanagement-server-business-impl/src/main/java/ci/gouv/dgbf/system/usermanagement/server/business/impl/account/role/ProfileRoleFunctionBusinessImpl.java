package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileRoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileRoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileRoleFunction;

@ApplicationScoped
public class ProfileRoleFunctionBusinessImpl extends AbstractBusinessEntityImpl<ProfileRoleFunction, ProfileRoleFunctionPersistence> implements ProfileRoleFunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ProfileRoleFunction> __getPersistenceEntityClass__() {
		return ProfileRoleFunction.class;
	}
	
}
