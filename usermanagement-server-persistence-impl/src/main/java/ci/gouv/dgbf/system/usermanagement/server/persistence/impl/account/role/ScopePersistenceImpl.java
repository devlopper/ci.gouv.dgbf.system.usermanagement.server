package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.rest.RestHelper;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractPersistenceIdentifiedByStringImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;

@ApplicationScoped
public class ScopePersistenceImpl extends AbstractPersistenceIdentifiedByStringImpl<Scope,ScopeHierarchy,ScopeHierarchies,ScopeHierarchyPersistence> implements ScopePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByTypeCodeByCode;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByTypeCodeByCode, "SELECT tuple FROM Scope tuple WHERE tuple.type.code = :typeCode AND tuple.code = :code");
	}
	
	@Override
	public Scope readByTypeCodeByCode(String typeCode, String code) {
		try {
			return __inject__(EntityManager.class).createNamedQuery(readByTypeCodeByCode, Scope.class).setParameter("typeCode", typeCode).setParameter("code", code).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}
	
	@Override
	protected void __listenExecuteCreateBefore__(Scope scope, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateBefore__(scope, properties, function);
		if(scope.getCode() == null)
			scope.setCode(scope.getIdentifier());
	}
	
	@Override
	protected void __listenExecuteReadAfter__(Collection<Scope> scopes, Properties properties) {
		super.__listenExecuteReadAfter__(scopes, properties);
		__setFieldsValuesFromUniformResourceIdentifiers__(scopes);
	}
	
	private static void __setFieldsValuesFromUniformResourceIdentifiers__(Collection<Scope> scopes) {
		if(CollectionHelper.isEmpty(scopes))
			return;
		Collection<ScopeType> scopeTypes = scopes.stream().map(Scope::getType).collect(Collectors.toSet());
		if(CollectionHelper.isEmpty(scopeTypes))
			return;
		for(ScopeType scopeType : scopeTypes) {
			Collection<Scope> __scopes__ = null;
			try {
				__scopes__ = RestHelper.getMany(Scope.class,scopeType.getCode());
				//InstanceGetter.getInstance().getFromUniformResourceIdentifier(Scope.class,(Object)scopeType.getCode()
				//		, ConfigurationHelper.getValueAsString(VariableName.getScopeFieldCode(scopeType))
				//		,ConfigurationHelper.getValueAsString(VariableName.getScopeFieldName(scopeType)));
			} catch (Exception exception) {
				exception.printStackTrace();
				continue;
			}
			if(CollectionHelper.isEmpty(__scopes__))
				continue;
			for(Scope index : scopes) {
				if(!index.getType().equals(scopeType))
					continue;
				for(Scope scope : __scopes__) {
					if(index.getCode().equals(scope.getCode())) {
						index.setName(scope.getName());
					}
				}
			}	
		}
	}
}
