package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractPersistenceIdentifiedByStringAndCodedImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;

@ApplicationScoped
public class PrivilegePersistenceImpl extends AbstractPersistenceIdentifiedByStringAndCodedImpl<Privilege,PrivilegeHierarchy,PrivilegeHierarchies,PrivilegeHierarchyPersistence> implements PrivilegePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	/*
	private String readByProfilesIdentifiers;

	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByProfilesIdentifiers, "SELECT tuple FROM Privilege tuple WHERE"
				+ " EXISTS(SELECT subTuple FROM ProfilePrivilege subTuple.profile.identifier IN :profilesIdentifiers)");
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, Privilege.FIELD_TYPE)))
				return readByProfilesIdentifiers;
		}
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByProfilesIdentifiers)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects)) {
				objects = new Object[] {queryContext.getFilterByKeysValue(Profile.FIELD_TYPE)};
			}
			return new Object[]{"codes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	*/
	
}
