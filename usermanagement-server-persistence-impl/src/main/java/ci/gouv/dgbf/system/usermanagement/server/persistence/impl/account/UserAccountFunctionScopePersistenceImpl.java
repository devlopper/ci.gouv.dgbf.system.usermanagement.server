package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.server.persistence.query.PersistenceQuery;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountFunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class UserAccountFunctionScopePersistenceImpl extends AbstractPersistenceEntityImpl<UserAccountFunctionScope> implements UserAccountFunctionScopePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readByUserAccount;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByUserAccount, __instanciateQueryReadBy__(UserAccountFunctionScope.FIELD_USER_ACCOUNT));
	}
	
	@Override
	public PersistenceServiceProvider<UserAccountFunctionScope> create(UserAccountFunctionScope userAccountFunctionScope, Properties properties) {
		super.create(userAccountFunctionScope, properties);
		__inject__(KeycloakHelper.class).addUserAccountAttributesValues(userAccountFunctionScope);
		return this;	
	}
	
	@Override
	public Collection<UserAccountFunctionScope> readByUserAccount(UserAccount userAccount) {
		Properties properties = new Properties().setQueryIdentifier(readByUserAccount);
		return __readMany__(properties,____getQueryParameters____(properties,userAccount));
	}
	
	@Override
	public PersistenceServiceProvider<UserAccountFunctionScope> update(UserAccountFunctionScope userAccountFunctionScope, Properties properties) {
		super.update(userAccountFunctionScope, properties);
		__inject__(KeycloakHelper.class).addUserAccountAttributesValues(userAccountFunctionScope);
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<UserAccountFunctionScope> delete(UserAccountFunctionScope userAccountFunctionScope, Properties properties) {
		super.delete(userAccountFunctionScope, properties);
		__inject__(KeycloakHelper.class).removeUserAccountAttributesValues(userAccountFunctionScope);
		return this;
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQuery query, Properties properties, Object... objects) {
		if(query.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByUserAccount))
			return new Object[]{UserAccountFunctionScope.FIELD_USER_ACCOUNT, objects[0]};
		return super.__getQueryParameters__(query, properties, objects);
	}
	
	@Override
	public Class<UserAccountFunctionScope> getEntityClass() {
		return UserAccountFunctionScope.class;
	}
}
