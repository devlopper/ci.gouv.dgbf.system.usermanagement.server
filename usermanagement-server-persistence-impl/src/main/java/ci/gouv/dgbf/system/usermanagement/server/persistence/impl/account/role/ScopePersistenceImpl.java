package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.properties.Properties;
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

	@Override
	protected void __listenExecuteReadAfter__(Collection<Scope> scopes, Properties properties) {
		super.__listenExecuteReadAfter__(scopes, properties);
		__setFieldsValuesFromUniformResourceIdentifiers__(scopes);
	}
	
	private static void __setFieldsValuesFromUniformResourceIdentifiers__(Collection<Scope> scopes) {
		if(CollectionHelper.isEmpty(scopes))
			return;
		Collection<ScopeType> scopeTypes = scopes.stream().map(Scope::getType).collect(Collectors.toList());
		if(CollectionHelper.isEmpty(scopeTypes))
			return;
		Collection<Scope> __scopes__ = InstanceGetter.getInstance().getFromUniformResourceIdentifiers(Scope.class
				,scopeTypes.stream().map(ScopeType::getCode).collect(Collectors.toSet()), "code","libelle");
		if(CollectionHelper.isEmpty(__scopes__))
			return;
		for(Scope index : scopes) {
			for(Scope scope : __scopes__) {
				if(index.getIdentifier().equals(scope.getIdentifier())) {
					index.setName(scope.getName());
				}
			}
		}
	}
}
