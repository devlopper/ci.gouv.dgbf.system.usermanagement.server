package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileRoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;

@ApplicationScoped
public class RoleFunctionBusinessImpl extends AbstractBusinessEntityImpl<RoleFunction, RoleFunctionPersistence> implements RoleFunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<RoleFunction> __getPersistenceEntityClass__() {
		return RoleFunction.class;
	}

	@Override
	protected void __listenExecuteCreateOneAfter__(RoleFunction roleFunction, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateOneAfter__(roleFunction, properties, function);
		if(Boolean.TRUE.equals(roleFunction.getIsProfileCreatableOnCreate())) {
			Profile profile = new Profile();
			profile.setCode(roleFunction.getCode());
			profile.setName(roleFunction.getName());
			__create__(profile);
			ProfileRoleFunction profileRoleFunction = new ProfileRoleFunction();
			profileRoleFunction.setProfile(profile);
			profileRoleFunction.setFunction(roleFunction);
			__create__(profileRoleFunction);
		}
	}
	
}
