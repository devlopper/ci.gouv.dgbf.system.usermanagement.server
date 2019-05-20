package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@Singleton
public class UserAccountPersistenceImpl extends AbstractPersistenceEntityImpl<UserAccount> implements UserAccountPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public PersistenceServiceProvider<UserAccount> create(UserAccount userAccount, Properties properties) {
		String identifier = __inject__(KeycloakHelper.class).createUserAccount(userAccount.getUser().getFirstName(), userAccount.getUser().getLastNames()
				, userAccount.getUser().getElectronicMailAddress(), userAccount.getAccount().getIdentifier(),  userAccount.getAccount().getPass()
				,  __injectCollectionHelper__().isEmpty(userAccount.getRolePostes()) ? null : userAccount.getRolePostes().get().stream().map(RolePoste::getIdentifier).collect(Collectors.toList()));
		userAccount.setIdentifier(identifier);
		super.create(userAccount, properties);
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<UserAccount> delete(UserAccount userAccount, Properties properties) {
		__inject__(KeycloakHelper.class).deleteUserAccount(userAccount.getIdentifier());
		return super.delete(userAccount,properties);
	}
}
