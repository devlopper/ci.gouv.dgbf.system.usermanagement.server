package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.server.persistence.query.PersistenceQuery;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class UserAccountPersistenceImpl extends AbstractPersistenceEntityImpl<UserAccount> implements UserAccountPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readWhereAccountIdentifierOrFirstUserFirstNameOrUserLastnamesContains;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readWhereAccountIdentifierOrFirstUserFirstNameOrUserLastnamesContains, "SELECT tuple FROM UserAccount tuple WHERE lower(tuple.account.identifier)"
				+ " LIKE lower(:query) OR lower(tuple.user.firstName) LIKE lower(:query) OR lower(tuple.user.lastNames) LIKE lower(:query)");
	}
	
	@Override
	public PersistenceServiceProvider<UserAccount> create(UserAccount userAccount, Properties properties) {
		if(Boolean.TRUE.equals(__injectValueHelper__().defaultToIfNull(userAccount.getIsPersistToKeycloakOnCreate(),Boolean.TRUE))) {
			String identifier = __inject__(KeycloakHelper.class).saveUserAccount(userAccount);
			userAccount.setIdentifier(identifier);	
		}
		super.create(userAccount, properties);
		return this;
	}
	
	@Override
	public UserAccount readByAccountIdentifier(String accountIdentifier) {
		return __readOne__(____getQueryParameters____(null,accountIdentifier));
	}
	
	@Override
	public PersistenceServiceProvider<UserAccount> update(UserAccount userAccount, Properties properties) {
		super.update(userAccount, properties);
		__inject__(KeycloakHelper.class).saveUserAccount(userAccount);
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<UserAccount> delete(UserAccount userAccount, Properties properties) {
		__inject__(KeycloakHelper.class).deleteUserAccount(userAccount.getIdentifier());
		return super.delete(userAccount,properties);
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQuery query, Properties properties, Object... objects) {
		if(query.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereAccountIdentifierOrFirstUserFirstNameOrUserLastnamesContains))
			return new Object[]{"accountIdentifier", objects[0]};
		return super.__getQueryParameters__(query, properties, objects);
	}
	
}
