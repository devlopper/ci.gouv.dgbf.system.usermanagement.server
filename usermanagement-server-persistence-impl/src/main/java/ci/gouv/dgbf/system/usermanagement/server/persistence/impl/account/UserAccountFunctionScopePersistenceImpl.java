package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.PersistenceFunctionModifier;
import org.cyk.utility.server.persistence.PersistenceFunctionRemover;
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
	protected void __listenExecuteCreateAfter__(UserAccountFunctionScope userAccountFunctionScope, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateAfter__(userAccountFunctionScope, properties, function);
		__inject__(KeycloakHelper.class).addUserAccountAttributesValues(userAccountFunctionScope);
	}
	
	@Override
	public Collection<UserAccountFunctionScope> readByUserAccount(UserAccount userAccount) {
		Properties properties = new Properties().setQueryIdentifier(readByUserAccount);
		return __readMany__(properties,____getQueryParameters____(properties,userAccount));
	}
	
	@Override
	protected void __listenExecuteUpdateAfter__(UserAccountFunctionScope userAccountFunctionScope, Properties properties,PersistenceFunctionModifier function) {
		super.__listenExecuteUpdateAfter__(userAccountFunctionScope, properties, function);
		__inject__(KeycloakHelper.class).addUserAccountAttributesValues(userAccountFunctionScope);
	}
	
	@Override
	protected void __listenExecuteDeleteAfter__(UserAccountFunctionScope userAccountFunctionScope, Properties properties,PersistenceFunctionRemover function) {
		super.__listenExecuteDeleteAfter__(userAccountFunctionScope, properties, function);
		__inject__(KeycloakHelper.class).removeUserAccountAttributesValues(userAccountFunctionScope);
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
