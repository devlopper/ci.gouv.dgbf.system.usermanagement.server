package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.server.persistence.query.PersistenceQuery;
import org.cyk.utility.server.persistence.query.PersistenceQueryRepository;

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
	public PersistenceServiceProvider<UserAccountFunctionScope> create(UserAccountFunctionScope userAccountRolePoste, Properties properties) {
		super.create(userAccountRolePoste, properties);
		__inject__(KeycloakHelper.class).addUserAccountAttributesValues(userAccountRolePoste);
		return this;	
	}
	
	@Override
	public Collection<UserAccountFunctionScope> readByUserAccount(UserAccount userAccount) {
		Properties properties = new Properties().setQueryIdentifier(readByUserAccount);
		return __readMany__(properties,____getQueryParameters____(properties,userAccount));
	}
	
	@Override
	public PersistenceServiceProvider<UserAccountFunctionScope> update(UserAccountFunctionScope userAccountRolePoste, Properties properties) {
		super.update(userAccountRolePoste, properties);
		__inject__(KeycloakHelper.class).addUserAccountAttributesValues(userAccountRolePoste);
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<UserAccountFunctionScope> delete(UserAccountFunctionScope userAccountRolePoste, Properties properties) {
		super.delete(userAccountRolePoste, properties);
		__inject__(KeycloakHelper.class).removeUserAccountAttributesValues(userAccountRolePoste);
		return this;
	}
	
	protected Object[] __getQueryParameters__(String queryIdentifier,Properties properties,Object...objects){
		PersistenceQuery persistenceQuery = __inject__(PersistenceQueryRepository.class).getBySystemIdentifier(queryIdentifier);
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByUserAccount,queryIdentifier))
			return new Object[]{UserAccountFunctionScope.FIELD_USER_ACCOUNT, objects[0]};
		return super.__getQueryParameters__(queryIdentifier,properties, objects);
	}
	
	@Override
	public Class<UserAccountFunctionScope> getEntityClass() {
		return UserAccountFunctionScope.class;
	}
}
