package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractPersistenceIdentifiedByStringAndCodedImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;

@ApplicationScoped
public class PrivilegePersistenceImpl extends AbstractPersistenceIdentifiedByStringAndCodedImpl<Privilege,PrivilegeHierarchy,PrivilegeHierarchies,PrivilegeHierarchyPersistence> implements PrivilegePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readByProfilesIdentifiers,readByUserAccountsIdentifiers;

	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByProfilesIdentifiers, "SELECT tuple FROM Privilege tuple WHERE"
				+ " EXISTS(SELECT subTuple FROM ProfilePrivilege subTuple WHERE subTuple.profile.identifier IN :profilesIdentifiers)");
		addQueryCollectInstances(readByUserAccountsIdentifiers, "SELECT tuple FROM Privilege tuple WHERE "
				+ " EXISTS(SELECT subTuple FROM ProfilePrivilege subTuple WHERE subTuple.privilege = tuple AND "
				+ " EXISTS(SELECT subSubTuple FROM UserAccountProfile subSubTuple WHERE subSubTuple.profile = subTuple.profile AND subSubTuple.userAccount.identifier IN :userAccountsIdentifiers) )");
	}
	
	@Override
	public Collection<Privilege> readByUserAccountsIdentifiers(Collection<String> userAccountsIdentifiers,Properties properties) {
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByUserAccountsIdentifiers);
		return __readMany__(properties, ____getQueryParameters____(properties,userAccountsIdentifiers));
	}

	@Override
	public Collection<Privilege> readByUserAccountsIdentifiers(Collection<String> userAccountsIdentifiers) {
		return readByUserAccountsIdentifiers(userAccountsIdentifiers,null);
	}

	@Override
	public Collection<Privilege> readByUserAccountsIdentifiers(Properties properties,String... userAccountsIdentifiers) {
		return readByUserAccountsIdentifiers(CollectionHelper.listOf(userAccountsIdentifiers),properties);
	}

	@Override
	public Collection<Privilege> readByUserAccountsIdentifiers(String... userAccountsIdentifiers) {
		return readByUserAccountsIdentifiers(null,userAccountsIdentifiers);
	}

	@Override
	public Collection<Privilege> readByUserAccounts(Collection<UserAccount> userAccounts,Properties properties) {
		return userAccounts == null ? null : readByUserAccountsIdentifiers(userAccounts.stream().map(UserAccount::getIdentifier).collect(Collectors.toList()),properties);
	}

	@Override
	public Collection<Privilege> readByUserAccounts(Collection<UserAccount> userAccounts) {
		return readByUserAccounts(userAccounts,null);
	}

	@Override
	public Collection<Privilege> readByUserAccounts(Properties properties, UserAccount... userAccounts) {
		return readByUserAccounts(CollectionHelper.listOf(userAccounts),properties);
	}

	@Override
	public Collection<Privilege> readByUserAccounts(UserAccount... userAccounts) {
		return readByUserAccounts(null,userAccounts);
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, Privilege.FIELD_PROFILES)))
				return readByProfilesIdentifiers;
		}
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByProfilesIdentifiers)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects)) {
				objects = new Object[] {queryContext.getFilterByKeysValue(Privilege.FIELD_PROFILES)};
			}
			return new Object[]{"profilesIdentifiers",objects[0]};
		}else if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByUserAccountsIdentifiers)) {	
			return new Object[]{"userAccountsIdentifiers",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
	
}
