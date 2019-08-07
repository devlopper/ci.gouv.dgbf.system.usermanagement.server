package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account;

import java.util.Collection;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;

public interface UserFunctionPersistence extends PersistenceEntity<UserFunction> {

	Collection<UserFunction> readByFunctionsCodes(Collection<String> functionsCodes);
	Collection<UserFunction> readByFunctionsCodes(String...functionsCodes);
	Collection<UserFunction> readByFunctions(Collection<Function> functions);
	
}
