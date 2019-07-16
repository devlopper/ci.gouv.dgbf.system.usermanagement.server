package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;
import org.cyk.utility.server.business.BusinessFunctionRemover;
import org.cyk.utility.string.Strings;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountFunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountFunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

@ApplicationScoped
public class UserAccountBusinessImpl extends AbstractBusinessEntityImpl<UserAccount, UserAccountPersistence> implements UserAccountBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<UserAccount> __getPersistenceEntityClass__() {
		return UserAccount.class;
	}
	
	@Override
	protected void __listenExecuteCreateBefore__(UserAccount userAccount, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(userAccount, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				__create__(userAccount.getUser());
				__create__(userAccount.getAccount());
			}
		});
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(UserAccount userAccount, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(userAccount, properties, function);
		if(Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(userAccount.getProfiles()))) {
			Collection<UserAccountProfile> userAccountProfiles = new ArrayList<>();
			for(Profile index : userAccount.getProfiles().get())
				userAccountProfiles.add(new UserAccountProfile().setUserAccount(userAccount).setProfile(index));
			__inject__(UserAccountProfileBusiness.class).createMany(userAccountProfiles);
		}
		
		if(Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(userAccount.getFunctionScopes()))) {
			Collection<UserAccountFunctionScope> userAccountRolePostes = new ArrayList<>();
			for(FunctionScope index : userAccount.getFunctionScopes().get())
				userAccountRolePostes.add(new UserAccountFunctionScope().setUserAccount(userAccount).setFunctionScope(index));
			__inject__(UserAccountFunctionScopeBusiness.class).createMany(userAccountRolePostes);
		}
		
		//Notification
		/*__produceMail__("SIB - Création de compte utilisateur", userAccount.getUser().getFirstName()+" "+userAccount.getUser().getLastNames()
				+" , un compte utilisateur a été créé avec succès. Le nom utilisateur est : "+userAccount.getAccount().getIdentifier()
				+" et le mot de passe est : "+userAccount.getAccount().getPass(), Arrays.asList(userAccount.getUser().getElectronicMailAddress()));
		*/
	}
	
	@Override
	protected void __processAfterRead__(UserAccount userAccount,Properties properties) {
		super.__processAfterRead__(userAccount,properties);
		Strings fields = __getFieldsFromProperties__(properties);
		if(__injectCollectionHelper__().isNotEmpty(fields))
			fields.get().forEach(new Consumer<String>() {
				@Override
				public void accept(String field) {
					if(UserAccount.FIELD_PROFILES.equals(field)) {
						Collection<UserAccountProfile> userAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
						if(__injectCollectionHelper__().isNotEmpty(userAccountProfiles))
							userAccount.getProfiles(Boolean.TRUE).add(userAccountProfiles.stream().map(UserAccountProfile::getProfile).collect(Collectors.toList()));
					}else if(UserAccount.FIELD_FUNCTION_SCOPES.equals(field)) {
						Collection<UserAccountFunctionScope> userAccountRolePostes = __inject__(UserAccountFunctionScopePersistence.class).readByUserAccount(userAccount);
						if(__injectCollectionHelper__().isNotEmpty(userAccountRolePostes))
							userAccount.getFunctionScopes(Boolean.TRUE).add(userAccountRolePostes.stream().map(UserAccountFunctionScope::getFunctionScope).collect(Collectors.toList()));
					}
				}
			});
	}
	
	@Override
	protected void __listenExecuteUpdateBefore__(UserAccount userAccount, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateBefore__(userAccount, properties, function);
		Strings fields = __getFieldsFromProperties__(properties);
		if(__injectCollectionHelper__().isNotEmpty(fields)) {
			for(String index : fields.get()) {
				if(UserAccount.FIELD_PROFILES.equals(index)) {
					Collection<UserAccountProfile> databaseUserAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
					Collection<Profile> databaseProfiles = __injectCollectionHelper__().isEmpty(databaseUserAccountProfiles) ? null : databaseUserAccountProfiles.stream()
							.map(UserAccountProfile::getProfile).collect(Collectors.toList());
					
					__delete__(userAccount.getProfiles(), databaseUserAccountProfiles,UserAccountProfile.FIELD_PROFILE);
					__save__(UserAccountProfile.class,userAccount.getProfiles(), databaseProfiles, UserAccountProfile.FIELD_PROFILE, userAccount, UserAccountProfile.FIELD_USER_ACCOUNT);
				}else if(UserAccount.FIELD_FUNCTION_SCOPES.equals(index)) {
					//__inject__(UserBusiness.class).save(userAccount.getUser());
					//__inject__(AccountBusiness.class).save(userAccount.getAccount());
					
					Collection<UserAccountFunctionScope> databaseCollection = __inject__(UserAccountFunctionScopePersistence.class).readByUserAccount(userAccount);
					Collection<FunctionScope> databaseFunctionScopes = __injectCollectionHelper__().isEmpty(databaseCollection) ? null : databaseCollection.stream()
							.map(UserAccountFunctionScope::getFunctionScope).collect(Collectors.toList());
					__delete__(userAccount.getFunctionScopes(), databaseCollection,UserAccountFunctionScope.FIELD_FUNCTION_SCOPE);
					__save__(UserAccountFunctionScope.class,userAccount.getFunctionScopes(), databaseFunctionScopes, UserAccountFunctionScope.FIELD_FUNCTION_SCOPE, userAccount, UserAccountFunctionScope.FIELD_USER_ACCOUNT);
				}
			}
		}	
	}
	
	@Override
	protected void __listenExecuteDeleteBefore__(UserAccount userAccount, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteBefore__(userAccount, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				__deleteMany__(__inject__(UserAccountFunctionScopePersistence.class).readByUserAccount(userAccount));
				__deleteMany__(__inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount));
			}
		});
		
		function.addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				__delete__(userAccount.getUser());
				__delete__(userAccount.getAccount());
			}
		});
	}
	
	@Override
	protected Boolean __isCallDeleteByInstanceOnDeleteByIdentifier__() {
		return Boolean.TRUE;
	}

}
