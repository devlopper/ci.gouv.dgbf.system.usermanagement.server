package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.Strings;
import org.cyk.utility.__kernel__.value.ValueHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.PersistenceFunctionModifier;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.PersistenceFunctionRemover;
import org.cyk.utility.__kernel__.persistence.query.QueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountFunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class UserAccountPersistenceImpl extends AbstractPersistenceEntityImpl<UserAccount> implements UserAccountPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readByAccountIdentifier,readWhereAccountIdentifierOrFirstUserFirstNameOrUserLastnamesContains;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAccountIdentifier, "SELECT tuple FROM UserAccount tuple WHERE tuple.account.identifier = :accountIdentifier");
		addQueryCollectInstances(readWhereAccountIdentifierOrFirstUserFirstNameOrUserLastnamesContains, "SELECT tuple FROM UserAccount tuple WHERE lower(tuple.account.identifier)"
				+ " LIKE lower(:query) OR lower(tuple.user.firstName) LIKE lower(:query) OR lower(tuple.user.lastNames) LIKE lower(:query)");
	}
	
	@Override
	protected void __listenExecuteCreateBefore__(UserAccount userAccount, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateBefore__(userAccount, properties, function);
		/*if(Boolean.TRUE.equals(ValueHelper.defaultToIfNull(userAccount.getIsPersistToKeycloakOnCreate(),Boolean.TRUE))) {
			String identifier = __inject__(KeycloakHelper.class).saveUserAccount(userAccount);
			userAccount.setIdentifier(identifier);	
		}*/
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(UserAccount userAccount, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateAfter__(userAccount, properties, function);
		if(Boolean.TRUE.equals(ValueHelper.defaultToIfNull(userAccount.getIsPersistToKeycloakOnCreate(),Boolean.TRUE)))
			__inject__(KeycloakHelper.class).createUserAccounts(userAccount);
	}
	
	@Override
	public UserAccount readByAccountIdentifier(String accountIdentifier) {
		Properties properties = new Properties();
		properties.setQueryIdentifier(readByAccountIdentifier);
		return __readOne__(properties,____getQueryParameters____(properties,accountIdentifier));
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(UserAccount userAccount, Field field, Properties properties) {
		if(UserAccount.FIELD_PROFILES.equals(field.getName())) {
			Collection<UserAccountProfile> userAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
			if(CollectionHelper.isNotEmpty(userAccountProfiles))
				userAccount.addProfiles(userAccountProfiles.stream().map(UserAccountProfile::getProfile).collect(Collectors.toList()));
		}else if(UserAccount.FIELD_FUNCTION_SCOPES.equals(field.getName())) {
			Collection<UserAccountFunctionScope> userAccountRolePostes = __inject__(UserAccountFunctionScopePersistence.class).readByUserAccount(userAccount);
			if(CollectionHelper.isNotEmpty(userAccountRolePostes))
				userAccount.addFunctionScopes(userAccountRolePostes.stream().map(UserAccountFunctionScope::getFunctionScope).collect(Collectors.toList()));
		}else if((UserAccount.FIELD_FUNCTIONS).equals(field.getName())) {
			__inject__(UserPersistence.class).setFunctions(userAccount.getUser());
			userAccount.setFunctions(userAccount.getUser().getFunctions());
		}else if((UserAccount.FIELD_SCOPES).equals(field.getName())) {
			Collection<UserAccountScope> userAccountScopes = __inject__(UserAccountScopePersistence.class).readByUserAccounts(userAccount);
			if(CollectionHelper.isNotEmpty(userAccountScopes))
				userAccount.addScopes(userAccountScopes.stream().map(UserAccountScope::getScope).collect(Collectors.toList()));
		}else if((UserAccount.FIELD_PRIVILEGES).equals(field.getName())) {
			Collection<Privilege> privileges = __inject__(PrivilegePersistence.class).readByUserAccounts(userAccount);
			if(CollectionHelper.isNotEmpty(privileges))
				userAccount.addPrivileges(privileges);
		}/*else if((UserAccount.FIELD_SYSTEM_PROFILES).equals(field.getName())) {
			Collection<UserAccountProfile> userAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
			if(CollectionHelper.isNotEmpty(userAccountProfiles)) {
				//Collection<Profile> userProfiles = userAccountProfiles.stream().map(UserAccountProfile::getProfile).collect(Collectors.toList());
				
				//userAccount.getProfiles(Boolean.TRUE).add(userAccountProfiles.stream().map(UserAccountProfile::getProfile).collect(Collectors.toList()));
			}
		}*/
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(UserAccount userAccount, String fieldName,Properties properties) {
		if(userAccount.getUser()!=null && (UserAccount.FIELD_USER+"."+User.FIELD_FUNCTIONS).equals(fieldName)) {
			Collection<UserFunction> userFunctions = __inject__(UserFunctionPersistence.class).readByUsers(userAccount.getUser());
			if(CollectionHelper.isNotEmpty(userFunctions))
				userAccount.getUser().addFunctions(userFunctions.stream().map(UserFunction::getFunction).collect(Collectors.toList()));
		}else if(CollectionHelper.isNotEmpty(userAccount.getProfiles()) && ("profile.privileges").equals(fieldName)) {
			for(Profile index : userAccount.getProfiles()) {
				Collection<ProfilePrivilege> profilePrivileges = __inject__(ProfilePrivilegePersistence.class).readByProfiles(index);
				if(CollectionHelper.isNotEmpty(profilePrivileges))
					index.addPrivileges(profilePrivileges.stream().map(ProfilePrivilege::getPrivilege).collect(Collectors.toList()));	
			}
		}
	}
	
	@Override
	protected void __listenExecuteReadAfter__(UserAccount userAccount, Properties properties) {
		super.__listenExecuteReadAfter__(userAccount, properties);
		Strings fields = __getFieldsFromProperties__(properties);
		if(CollectionHelper.isNotEmpty(fields)) {
			for(String index : fields.get()) {
				if((Profile.FIELD_PRIVILEGES).equals(index)) {
					if(CollectionHelper.isNotEmpty(userAccount.getProfiles()))
						for(Profile profile : userAccount.getProfiles())
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
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, UserAccount.FIELD_ACCOUNT+"."+Account.FIELD_IDENTIFIER)))
				return readByAccountIdentifier;
		}
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
	@Override
	protected Object[] __getQueryParameters__(QueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAccountIdentifier))
			return new Object[]{"accountIdentifier", queryContext.getFilterByKeysValue(UserAccount.FIELD_ACCOUNT+"."+Account.FIELD_IDENTIFIER)};
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereAccountIdentifierOrFirstUserFirstNameOrUserLastnamesContains))
			return new Object[]{"accountIdentifier", objects[0]};
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
}
