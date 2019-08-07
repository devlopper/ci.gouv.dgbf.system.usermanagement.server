package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.constant.ConstantCharacter;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.filter.Filter;
import org.cyk.utility.string.StringHelper;
import org.cyk.utility.string.Strings;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;

@ApplicationScoped
public class UserPersistenceImpl extends AbstractPersistenceEntityImpl<User> implements UserPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenExecuteReadAfter__(User user, Properties properties) {
		super.__listenExecuteReadAfter__(user, properties);
		Strings fields = __getFieldsFromProperties__(properties);
		if(__injectCollectionHelper__().isNotEmpty(fields)) {
			for(String index : fields.get()) {
				if(User.FIELD_NAMES.equals(index)) {
					String names = user.getFirstName();
					if(__inject__(StringHelper.class).isNotBlank(user.getLastNames()))
						names = names + ConstantCharacter.SPACE + user.getLastNames();
					user.setNames(names);
				}else if(User.FIELD_FUNCTIONS.equals(index)) {
					Filter filter = __inject__(Filter.class).setKlass(UserFunction.class).addField(UserFunction.FIELD_USER, Arrays.asList(user.getIdentifier()));
					Collection<UserFunction> userFunctions = __inject__(UserFunctionPersistence.class).read(new Properties().setQueryFilters(filter));
					if(__injectCollectionHelper__().isNotEmpty(userFunctions))
						user.setFunctions(__inject__(Functions.class).add(userFunctions.stream().map(UserFunction::getFunction).collect(Collectors.toList())));
				}
			}
		}
	}
	
}
