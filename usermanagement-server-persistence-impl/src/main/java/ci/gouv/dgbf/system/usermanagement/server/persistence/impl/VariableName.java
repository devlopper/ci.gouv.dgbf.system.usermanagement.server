package ci.gouv.dgbf.system.usermanagement.server.persistence.impl;

import org.cyk.utility.__kernel__.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;

public interface VariableName {

	static String getScopeFieldCode(String typeCode) {
		if(StringHelper.isBlank(typeCode))
			return null;
		return org.cyk.utility.__kernel__.configuration.VariableName.build(Scope.class,typeCode,"uri",Scope.FIELD_CODE);
	}

	static String getScopeFieldCode(ScopeType type) {
		if(type == null)
			return null;
		return getScopeFieldCode(type.getCode());
	}
	
	static String getScopeFieldName(String typeCode) {
		if(StringHelper.isBlank(typeCode))
			return null;
		return org.cyk.utility.__kernel__.configuration.VariableName.build(Scope.class,typeCode,"uri",Scope.FIELD_NAME);
	}

	static String getScopeFieldName(ScopeType type) {
		if(type == null)
			return null;
		return getScopeFieldName(type.getCode());
	}
}
