package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.PersistenceFunctionModifier;
import org.cyk.utility.server.persistence.PersistenceFunctionRemover;
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
	protected void __listenExecuteCreateBefore__(UserAccount userAccount, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateBefore__(userAccount, properties, function);
		if(Boolean.TRUE.equals(__injectValueHelper__().defaultToIfNull(userAccount.getIsPersistToKeycloakOnCreate(),Boolean.TRUE))) {
			String identifier = __inject__(KeycloakHelper.class).saveUserAccount(userAccount);
			userAccount.setIdentifier(identifier);	
		}
	}
	
	@Override
	public UserAccount readByAccountIdentifier(String accountIdentifier) {
		//Properties properties = new Properties();
		//properties.setQueryIdentifier(readByAccountIdentifier);
		return __readOne__(null,____getQueryParameters____(null,accountIdentifier));
	}
	
	@Override
	protected void __listenExecuteUpdateAfter__(UserAccount userAccount, Properties properties,PersistenceFunctionModifier function) {
		super.__listenExecuteUpdateAfter__(userAccount, properties, function);
		__inject__(KeycloakHelper.class).saveUserAccount(userAccount);
	}
	
	@Override
	protected void __listenExecuteDeleteAfter__(UserAccount userAccount, Properties properties,PersistenceFunctionRemover function) {
		super.__listenExecuteDeleteAfter__(userAccount, properties, function);
		__inject__(KeycloakHelper.class).deleteUserAccount(userAccount.getIdentifier());
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQuery query, Properties properties, Object... objects) {
		if(query.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereAccountIdentifierOrFirstUserFirstNameOrUserLastnamesContains))
			return new Object[]{"accountIdentifier", objects[0]};
		return super.__getQueryParameters__(query, properties, objects);
	}
	
}
