package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.PersistenceFunctionModifier;
import org.cyk.utility.server.persistence.PersistenceFunctionRemover;
import org.cyk.utility.server.persistence.query.PersistenceQuery;
import org.cyk.utility.string.Strings;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountFunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
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
		/*if(Boolean.TRUE.equals(__injectValueHelper__().defaultToIfNull(userAccount.getIsPersistToKeycloakOnCreate(),Boolean.TRUE))) {
			String identifier = __inject__(KeycloakHelper.class).saveUserAccount(userAccount);
			userAccount.setIdentifier(identifier);	
		}*/
	}
	
	@Override
	public UserAccount readByAccountIdentifier(String accountIdentifier) {
		//Properties properties = new Properties();
		//properties.setQueryIdentifier(readByAccountIdentifier);
		return __readOne__(null,____getQueryParameters____(null,accountIdentifier));
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(UserAccount userAccount, Field field, Properties properties) {
		if(UserAccount.FIELD_PROFILES.equals(field.getName())) {
			Collection<UserAccountProfile> userAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
			if(__injectCollectionHelper__().isNotEmpty(userAccountProfiles))
				userAccount.getProfiles(Boolean.TRUE).add(userAccountProfiles.stream().map(UserAccountProfile::getProfile).collect(Collectors.toList()));
		}else if(UserAccount.FIELD_FUNCTION_SCOPES.equals(field.getName())) {
			Collection<UserAccountFunctionScope> userAccountRolePostes = __inject__(UserAccountFunctionScopePersistence.class).readByUserAccount(userAccount);
			if(__injectCollectionHelper__().isNotEmpty(userAccountRolePostes))
				userAccount.getFunctionScopes(Boolean.TRUE).add(userAccountRolePostes.stream().map(UserAccountFunctionScope::getFunctionScope).collect(Collectors.toList()));
		}else if((UserAccount.FIELD_FUNCTIONS).equals(field.getName())) {
			__inject__(UserPersistence.class).setFunctions(userAccount.getUser());
			userAccount.setFunctions(userAccount.getUser().getFunctions());
		}/*else if((UserAccount.FIELD_SYSTEM_PROFILES).equals(field.getName())) {
			Collection<UserAccountProfile> userAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
			if(__injectCollectionHelper__().isNotEmpty(userAccountProfiles)) {
				//Collection<Profile> userProfiles = userAccountProfiles.stream().map(UserAccountProfile::getProfile).collect(Collectors.toList());
				
				//userAccount.getProfiles(Boolean.TRUE).add(userAccountProfiles.stream().map(UserAccountProfile::getProfile).collect(Collectors.toList()));
			}
		}*/
	}
	
	@Override
	protected void __listenExecuteReadAfter__(UserAccount userAccount, Properties properties) {
		super.__listenExecuteReadAfter__(userAccount, properties);
		Strings fields = __getFieldsFromProperties__(properties);
		if(__injectCollectionHelper__().isNotEmpty(fields)) {
			for(String index : fields.get()) {
				if((Profile.FIELD_PRIVILEGES).equals(index)) {
					if(__injectCollectionHelper__().isNotEmpty(userAccount.getProfiles()))
						for(Profile profile : userAccount.getProfiles().get())
							__inject__(ProfilePersistence.class).setPrivileges(profile);
				}
			}	
		}
	}
	
	@Override
	protected void __listenExecuteUpdateAfter__(UserAccount userAccount, Properties properties,PersistenceFunctionModifier function) {
		super.__listenExecuteUpdateAfter__(userAccount, properties, function);
		//__inject__(KeycloakHelper.class).saveUserAccount(userAccount);
	}
	
	@Override
	protected void __listenExecuteDeleteAfter__(UserAccount userAccount, Properties properties,PersistenceFunctionRemover function) {
		super.__listenExecuteDeleteAfter__(userAccount, properties, function);
		//__inject__(KeycloakHelper.class).deleteUserAccount(userAccount.getIdentifier());
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQuery query, Properties properties, Object... objects) {
		if(query.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereAccountIdentifierOrFirstUserFirstNameOrUserLastnamesContains))
			return new Object[]{"accountIdentifier", objects[0]};
		return super.__getQueryParameters__(query, properties, objects);
	}
	
}
