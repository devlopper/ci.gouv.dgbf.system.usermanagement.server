package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account;

import java.util.Collection;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;

public interface UserFunctionPersistence extends PersistenceEntity<UserFunction> {

	Collection<UserFunction> readByFunctionsCodes(Collection<String> functionsCodes,Properties properties);
	Collection<UserFunction> readByFunctionsCodes(Collection<String> functionsCodes);
	Collection<UserFunction> readByFunctionsCodes(Properties properties,String...functionsCodes);
	Collection<UserFunction> readByFunctionsCodes(String...functionsCodes);
	Collection<UserFunction> readByFunctions(Collection<Function> functions,Properties properties);
	Collection<UserFunction> readByFunctions(Collection<Function> functions);
	Collection<UserFunction> readByFunctions(Properties properties,Function...functions);
	Collection<UserFunction> readByFunctions(Function...functions);
	
	Collection<UserFunction> readByUsersIdentifiers(Collection<String> usersIdentifiers,Properties properties);
	Collection<UserFunction> readByUsersIdentifiers(Collection<String> usersIdentifiers);
	Collection<UserFunction> readByUsersIdentifiers(Properties properties,String...usersIdentifiers);
	Collection<UserFunction> readByUsersIdentifiers(String...usersIdentifiers);
	Collection<UserFunction> readByUsers(Collection<User> users,Properties properties);
	Collection<UserFunction> readByUsers(Collection<User> users);
	Collection<UserFunction> readByUsers(Properties properties,User...users);
	Collection<UserFunction> readByUsers(User...users);
	
}
