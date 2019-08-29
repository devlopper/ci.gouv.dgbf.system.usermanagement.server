package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;

@ApplicationScoped
public class UserAccountScopePersistenceImpl extends AbstractPersistenceEntityImpl<UserAccountScope> implements UserAccountScopePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByUserAccountsIdentifiers;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByUserAccountsIdentifiers, "SELECT tuple FROM UserAccountScope tuple WHERE tuple.userAccount.identifier IN :userAccountsIdentifiers");
	}

	@Override
	public Collection<UserAccountScope> readByUserAccountsIdentifiers(Collection<String> userAccountsIdentifiers,Properties properties) {
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByUserAccountsIdentifiers);
		return __readMany__(properties, ____getQueryParameters____(properties,userAccountsIdentifiers));
	}

	@Override
	public Collection<UserAccountScope> readByUserAccountsIdentifiers(Collection<String> userAccountsIdentifiers) {
		return readByUserAccountsIdentifiers(userAccountsIdentifiers,null);
	}

	@Override
	public Collection<UserAccountScope> readByUserAccountsIdentifiers(Properties properties,String... userAccountsIdentifiers) {
		return readByUserAccountsIdentifiers(__inject__(CollectionHelper.class).instanciate(userAccountsIdentifiers),properties);
	}

	@Override
	public Collection<UserAccountScope> readByUserAccountsIdentifiers(String... userAccountsIdentifiers) {
		return readByUserAccountsIdentifiers(null,userAccountsIdentifiers);
	}

	@Override
	public Collection<UserAccountScope> readByUserAccounts(Collection<UserAccount> userAccounts,Properties properties) {
		return userAccounts == null ? null : readByUserAccountsIdentifiers(userAccounts.stream().map(UserAccount::getIdentifier).collect(Collectors.toList()),properties);
	}

	@Override
	public Collection<UserAccountScope> readByUserAccounts(Collection<UserAccount> userAccounts) {
		return readByUserAccounts(userAccounts,null);
	}

	@Override
	public Collection<UserAccountScope> readByUserAccounts(Properties properties, UserAccount... userAccounts) {
		return readByUserAccounts(__inject__(CollectionHelper.class).instanciate(userAccounts),properties);
	}

	@Override
	public Collection<UserAccountScope> readByUserAccounts(UserAccount... userAccounts) {
		return readByUserAccounts(null,userAccounts);
	}
	
	/**/

	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByUserAccountsIdentifiers)) {	
			return new Object[]{"userAccountsIdentifiers",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}
