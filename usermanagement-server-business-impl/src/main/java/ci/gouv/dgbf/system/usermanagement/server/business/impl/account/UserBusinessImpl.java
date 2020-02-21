package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.Strings;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;
import org.cyk.utility.server.business.BusinessFunctionRemover;
import org.cyk.utility.__kernel__.persistence.query.filter.Filter;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;

@ApplicationScoped
public class UserBusinessImpl extends AbstractBusinessEntityImpl<User, UserPersistence> implements UserBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateAfter__(User user, Properties properties, BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(user, properties, function);
		if(Boolean.TRUE.equals(CollectionHelper.isNotEmpty(user.getFunctions()))) {
			Collection<UserFunction> userFunctions = new ArrayList<>();
			for(Function index : user.getFunctions())
				userFunctions.add(new UserFunction().setUser(user).setFunction(index));
			__inject__(UserFunctionBusiness.class).createMany(userFunctions);
		}
	}
	
	@Override
	protected void __listenExecuteUpdateBefore__(User user, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateBefore__(user, properties, function);
		Strings fields = __getFieldsFromProperties__(properties);
		if(CollectionHelper.isNotEmpty(fields)) {
			for(String index : fields.get()) {
				if(User.FIELD_FUNCTIONS.equals(index)) {
					Filter filter = __inject__(Filter.class).setKlass(UserFunction.class).addField(UserFunction.FIELD_USER, Arrays.asList(user.getIdentifier()));
					Collection<UserFunction> databaseUserFunctions = __inject__(UserFunctionPersistence.class).read(new Properties().setQueryFilters(filter));
					Collection<Function> databaseFunctions = CollectionHelper.isEmpty(databaseUserFunctions) ? null : databaseUserFunctions.stream()
							.map(UserFunction::getFunction).collect(Collectors.toList());
					
					__delete__(user.getFunctions(), databaseUserFunctions,UserFunction.FIELD_FUNCTION);
					__save__(UserFunction.class,user.getFunctions(), databaseFunctions, UserFunction.FIELD_FUNCTION, user, UserFunction.FIELD_USER);
					
				}
			}
		}	
	}
	
	@Override
	protected void __listenExecuteDeleteBefore__(User user, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteBefore__(user, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				Filter filter = __inject__(Filter.class).setKlass(UserFunction.class).addField(UserFunction.FIELD_USER, Arrays.asList(user.getIdentifier()));
				__deleteMany__(__inject__(UserFunctionPersistence.class).read(new Properties().setQueryFilters(filter)));
			}
		});
	}
	
	@Override
	protected Boolean __isCallDeleteByInstanceOnDeleteByIdentifier__() {
		return Boolean.TRUE;
	}
}
