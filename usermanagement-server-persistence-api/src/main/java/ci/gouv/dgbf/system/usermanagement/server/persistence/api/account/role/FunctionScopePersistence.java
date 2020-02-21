package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

public interface FunctionScopePersistence extends PersistenceEntity<FunctionScope> {

	FunctionScope readByFunctionCodeByScopeCode(String functionCode,String scopeCode,Properties properties);
	
	default FunctionScope readByFunctionCodeByScopeCode(String functionCode,String scopeCode) {
		if(StringHelper.isBlank(functionCode) || StringHelper.isBlank(scopeCode))
			return null;
		return readByFunctionCodeByScopeCode(functionCode, scopeCode,null);
	}
	
	default FunctionScope readByFunctionByScope(Function function,Scope scope,Properties properties) {
		if(function == null || scope == null)
			return null;
		return readByFunctionCodeByScopeCode(function.getCode(), scope.getCode(),properties);
	}
	
	default FunctionScope readByFunctionByScope(Function function,Scope scope) {
		if(function == null || scope == null)
			return null;
		return readByFunctionByScope(function, scope,null);
	}
}
