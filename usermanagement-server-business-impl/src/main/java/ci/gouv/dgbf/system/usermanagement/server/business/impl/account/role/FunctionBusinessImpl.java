package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionRemover;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;

@ApplicationScoped
public class FunctionBusinessImpl extends AbstractBusinessEntityImpl<Function, FunctionPersistence> implements FunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Function> __getPersistenceEntityClass__() {
		return Function.class;
	}

	@Override
	protected void __listenExecuteCreateAfter__(Function function, Properties properties,BusinessFunctionCreator functionCreator) {
		super.__listenExecuteCreateAfter__(function, properties, functionCreator);
		if(Boolean.TRUE.equals(__injectValueHelper__().defaultToIfNull(function.getIsProfileCreatableOnCreate(),Boolean.TRUE))) {
			Profile profile = new Profile();
			profile.setCode(function.getCode());
			profile.setName(function.getName());
			profile.setType(__inject__(ProfileTypePersistence.class).readByBusinessIdentifier(ProfileType.CODE_SYSTEM));
			__create__(profile);
			ProfileFunction profileFunction = new ProfileFunction();
			profileFunction.setProfile(profile);
			profileFunction.setFunction(function);
			__create__(profileFunction);
		}
	}
	
	@Override
	protected void __listenExecuteDeleteBefore__(Function function, Properties properties,BusinessFunctionRemover functionRemover) {
		super.__listenExecuteDeleteBefore__(function, properties, functionRemover);
		functionRemover.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				Collection<Function> functions = Arrays.asList(function);
				__deleteMany__(__inject__(ProfileFunctionPersistence.class).readByFunctions(functions));
			}
		});
	}
	
}
