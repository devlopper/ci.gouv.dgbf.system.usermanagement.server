package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.server.persistence.query.PersistenceQuery;
import org.cyk.utility.server.persistence.query.PersistenceQueryRepository;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@Singleton
public class UserAccountPersistenceImpl extends AbstractPersistenceEntityImpl<UserAccount> implements UserAccountPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readByAccountIdentifier;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAccountIdentifier, "SELECT tuple FROM UserAccount tuple WHERE lower(tuple.account.identifier) LIKE lower(:accountIdentifier)");
	}
	
	@Override
	public PersistenceServiceProvider<UserAccount> create(UserAccount userAccount, Properties properties) {
		Collection<String> rolesCodes = __injectCollectionHelper__().isEmpty(userAccount.getRolePostes()) ? null : userAccount.getRolePostes().get()
				.stream().map(x -> x.getFunction().getCode()).collect(Collectors.toList());
		
		String identifier = __inject__(KeycloakHelper.class).createUserAccount(userAccount.getUser().getFirstName(), userAccount.getUser().getLastNames()
				, userAccount.getUser().getElectronicMailAddress(), userAccount.getAccount().getIdentifier(),  userAccount.getAccount().getPass()
				,  rolesCodes);
		userAccount.setIdentifier(identifier);
		super.create(userAccount, properties);
		return this;
	}
	
	@Override
	public UserAccount readByAccountIdentifier(String accountIdentifier) {
		return __readOne__(____getQueryParameters____(null,accountIdentifier));
	}
	
	@Override
	public PersistenceServiceProvider<UserAccount> delete(UserAccount userAccount, Properties properties) {
		__inject__(KeycloakHelper.class).deleteUserAccount(userAccount.getIdentifier());
		return super.delete(userAccount,properties);
	}
	
	protected Object[] __getQueryParameters__(String queryIdentifier,Properties properties,Object...objects){
		PersistenceQuery persistenceQuery = __inject__(PersistenceQueryRepository.class).getBySystemIdentifier(queryIdentifier);
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAccountIdentifier,queryIdentifier))
			return new Object[]{"accountIdentifier", objects[0]};
		return super.__getQueryParameters__(queryIdentifier,properties, objects);
	}
}
