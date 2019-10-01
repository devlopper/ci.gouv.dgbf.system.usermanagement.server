package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.constant.ConstantCharacter;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;

@ApplicationScoped
public class UserPersistenceImpl extends AbstractPersistenceEntityImpl<User> implements UserPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(User user, Field field, Properties properties) {
		if(User.FIELD_NAMES.equals(field.getName())) {
			String names = user.getFirstName();
			if(StringHelper.isNotBlank(user.getLastNames()))
				names = names + ConstantCharacter.SPACE + user.getLastNames();
			user.setNames(names);
		}else if(User.FIELD_FUNCTIONS.equals(field.getName()))
			setFunctions(user);
	}
	
	@Override
	public UserPersistence setFunctions(User user) {
		Collection<UserFunction> userFunctions = __inject__(UserFunctionPersistence.class).readByUsers(user);
		if(CollectionHelper.isNotEmpty(userFunctions))
			user.setFunctions(__inject__(Functions.class).add(userFunctions.stream().map(UserFunction::getFunction).collect(Collectors.toList())));
		return this;
	}
	
}
