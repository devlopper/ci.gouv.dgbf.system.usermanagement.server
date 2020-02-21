package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.rest.RestHelper;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.business.BusinessEntity;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.hierarchy.AbstractBusinessIdentifiedByStringImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeFunction;

@ApplicationScoped
public class ScopeBusinessImpl extends AbstractBusinessIdentifiedByStringImpl<Scope, ScopePersistence,ScopeHierarchy,ScopeHierarchies,ScopeHierarchyPersistence,ScopeHierarchyBusiness> implements ScopeBusiness,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public BusinessEntity<Scope> import_(Properties properties) {
		Collection<ScopeType> scopeTypes = __inject__(ScopeTypePersistence.class).read();
		if(CollectionHelper.isEmpty(scopeTypes))
			return this;
		Collection<Scope> scopes = null;
		for(ScopeType scopeType : scopeTypes) {
			Collection<Scope> __scopes__ = RestHelper.getMany(Scope.class,scopeType.getCode());
			if(CollectionHelper.isEmpty(__scopes__))
				continue;
			if(scopes == null)
				scopes = new ArrayList<>();
			Collection<String> codes = new ArrayList<>();
			for(Scope scope : __scopes__) {
				String code = scope.getCode();
				if(StringHelper.isBlank(code))
					continue;
				if(!codes.contains(code) && __inject__(ScopePersistence.class).readByTypeCodeByCode(scopeType.getCode(), code) == null) {
					scopes.add(new Scope().setType(scopeType).setCode(code));
					codes.add(code);
				}
			}
		}
		saveByBatch(scopes,100);
		return this;
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(Scope scope, Properties properties, BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(scope, properties, function);
		Collection<ScopeTypeFunction> scopeTypeFunctions = __inject__(ScopeTypeFunctionPersistence.class).readByScopeTypes(scope.getType());
		if(CollectionHelper.isNotEmpty(scopeTypeFunctions)) {
			for(ScopeTypeFunction index : scopeTypeFunctions) {
				if(index.getFunctionCreatableOnScopeCreate()) {
					FunctionScope functionScope = __inject__(FunctionScopePersistence.class).readByFunctionByScope(index.getFunction(), scope);
					if(functionScope == null) {
						__inject__(FunctionScopeBusiness.class).create(new FunctionScope().setFunction(index.getFunction()).setScope(scope));
					}
				}
			}
		}
	}
	
}
