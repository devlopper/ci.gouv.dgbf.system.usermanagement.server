package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.Strings;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;
import org.cyk.utility.server.business.hierarchy.AbstractBusinessIdentifiedByStringAndCodedImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeFunction;

@ApplicationScoped
public class ScopeTypeBusinessImpl extends AbstractBusinessIdentifiedByStringAndCodedImpl<ScopeType, ScopeTypePersistence,ScopeTypeHierarchy,ScopeTypeHierarchies,ScopeTypeHierarchyPersistence,ScopeTypeHierarchyBusiness> implements ScopeTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateAfter__(ScopeType scopeType, Properties properties, BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(scopeType, properties, function);
		if(CollectionHelper.isNotEmpty(scopeType.getFunctions())) {
			Collection<ScopeTypeFunction> scopeTypeFunctions = scopeType.getFunctions().stream().map(x -> new ScopeTypeFunction(scopeType,x,Boolean.TRUE)).collect(Collectors.toList());
			__inject__(ScopeTypeFunctionBusiness.class).createMany(scopeTypeFunctions);
		}
	}
	
	@Override
	protected void __listenExecuteUpdateBefore__(ScopeType scopeType, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateBefore__(scopeType, properties, function);
		Strings fields = __getFieldsFromProperties__(properties);
		if(CollectionHelper.isEmpty(fields))
			return;		
		for(String index : fields.get()) {
			if(ScopeType.FIELD_FUNCTIONS.equals(index)) {
				Collection<ScopeTypeFunction> databaseScopeTypeFunctions = __inject__(ScopeTypeFunctionPersistence.class).readByScopeTypes(scopeType);
				Collection<Function> databaseFunctions = CollectionHelper.isEmpty(databaseScopeTypeFunctions) ? null : databaseScopeTypeFunctions.stream()
						.map(ScopeTypeFunction::getFunction).collect(Collectors.toList());
				
				__delete__(scopeType.getFunctions(), databaseScopeTypeFunctions,ScopeTypeFunction.FIELD_FUNCTION);
				__save__(ScopeTypeFunction.class,scopeType.getFunctions(), databaseFunctions, ScopeTypeFunction.FIELD_FUNCTION, scopeType, ScopeTypeFunction.FIELD_SCOPE_TYPE);
			}
		}
	}
	
	@Override
	protected <M, D> D __getSavableInstance__(Class<D> klass, M finalInstance, Collection<M> persistedInstances,String fieldName, Object master, String masterFieldName) {
		D instance = super.__getSavableInstance__(klass, finalInstance, persistedInstances, fieldName, master, masterFieldName);
		if(instance instanceof ScopeTypeFunction) {
			if(((ScopeTypeFunction)instance).getFunctionCreatableOnScopeCreate() == null)
				((ScopeTypeFunction)instance).setFunctionCreatableOnScopeCreate(Boolean.TRUE);
		}
		return instance;
	}
	
}
