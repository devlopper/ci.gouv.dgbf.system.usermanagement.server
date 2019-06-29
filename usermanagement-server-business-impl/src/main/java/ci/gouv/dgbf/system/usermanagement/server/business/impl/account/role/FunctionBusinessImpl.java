package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;

@ApplicationScoped
public class FunctionBusinessImpl extends AbstractBusinessEntityImpl<Function, FunctionPersistence> implements FunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Function> __getPersistenceEntityClass__() {
		return Function.class;
	}

	@Override
	protected void __listenExecuteCreateOneAfter__(Function function, Properties properties,BusinessFunctionCreator functionCreator) {
		super.__listenExecuteCreateOneAfter__(function, properties, functionCreator);
		if(Boolean.TRUE.equals(function.getIsProfileCreatableOnCreate())) {
			Profile profile = new Profile();
			profile.setCode(function.getCode());
			profile.setName(function.getName());
			__create__(profile);
			ProfileFunction profileFunction = new ProfileFunction();
			profileFunction.setProfile(profile);
			profileFunction.setFunction(function);
			__create__(profileFunction);
		}
	}
	
}
