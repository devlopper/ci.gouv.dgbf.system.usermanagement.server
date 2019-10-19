package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.__kernel__.string.Strings;
import org.cyk.utility.__kernel__.value.ValueHelper;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;
import org.cyk.utility.server.business.BusinessFunctionRemover;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountFunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountFunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

@ApplicationScoped
public class UserAccountBusinessImpl extends AbstractBusinessEntityImpl<UserAccount, UserAccountPersistence> implements UserAccountBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(UserAccount userAccount, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(userAccount, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				if(userAccount.getUser().getFunctions() == null)
					userAccount.getUser().setFunctions(userAccount.getFunctions());
				__create__(userAccount.getUser());
				__create__(userAccount.getAccount());
			}
		});
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(UserAccount userAccount, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(userAccount, properties, function);
		if(Boolean.TRUE.equals(CollectionHelper.isNotEmpty(userAccount.getScopes()))) {
			Collection<UserAccountScope> userAccountScopes = new ArrayList<>();
			for(Scope index : userAccount.getScopes())
				userAccountScopes.add(new UserAccountScope().setUserAccount(userAccount).setScope(index));
			__inject__(UserAccountScopeBusiness.class).createMany(userAccountScopes);
		}
		
		if(Boolean.TRUE.equals(CollectionHelper.isNotEmpty(userAccount.getProfiles()))) {
			Collection<UserAccountProfile> userAccountProfiles = new ArrayList<>();
			for(Profile index : userAccount.getProfiles())
				userAccountProfiles.add(new UserAccountProfile().setUserAccount(userAccount).setProfile(index));
			__inject__(UserAccountProfileBusiness.class).createMany(userAccountProfiles);
		}
		
		if(Boolean.TRUE.equals(CollectionHelper.isNotEmpty(userAccount.getFunctionScopes()))) {
			Collection<UserAccountFunctionScope> userAccountRolePostes = new ArrayList<>();
			for(FunctionScope index : userAccount.getFunctionScopes())
				userAccountRolePostes.add(new UserAccountFunctionScope().setUserAccount(userAccount).setFunctionScope(index));
			__inject__(UserAccountFunctionScopeBusiness.class).createMany(userAccountRolePostes);
		}
		
		if(Boolean.TRUE.equals(ValueHelper.defaultToIfNull(userAccount.getIsProfileCreatableOnCreate(),Boolean.TRUE))) {
			Profile profile = new Profile();
			profile.setCode(userAccount.getIdentifier());
			profile.setName(userAccount.getUser().getNames());
			if(StringHelper.isBlank(profile.getName()))
				profile.setName(profile.getCode());
			profile.setType(__inject__(ProfileTypePersistence.class).readByBusinessIdentifier(ProfileType.CODE_UTILISATEUR));
			__create__(profile);
			UserAccountProfile userAccountProfile = new UserAccountProfile();
			userAccountProfile.setUserAccount(userAccount);
			userAccountProfile.setProfile(profile);
			__create__(userAccountProfile);
		}
		
		//Notification
		/*__produceMail__("SIB - Création de compte utilisateur", userAccount.getUser().getFirstName()+" "+userAccount.getUser().getLastNames()
				+" , un compte utilisateur a été créé avec succès. Le nom utilisateur est : "+userAccount.getAccount().getIdentifier()
				+" et le mot de passe est : "+userAccount.getAccount().getPass(), Arrays.asList(userAccount.getUser().getElectronicMailAddress()));
		*/
	}
	
	@Override
	protected void __listenExecuteUpdateBefore__(UserAccount userAccount, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateBefore__(userAccount, properties, function);
		Strings fields = __getFieldsFromProperties__(properties);
		if(CollectionHelper.isEmpty(fields))
			return;
		
		//if(userAccount.getUser().getFunctions() == null)
		//	userAccount.getUser().setFunctions(userAccount.getFunctions());
		//find the fields to update in user : these field must starts with user.
		Collection<String> userFields = StringHelper.filter(fields.get(), "^user\\.", Boolean.TRUE);
		__inject__(UserBusiness.class).update(userAccount.getUser(),new Properties().setFields(userFields));
		
		__save__(userAccount.getAccount());
		
		if(CollectionHelper.isNotEmpty(fields)) {
			for(String index : fields.get()) {
				if(UserAccount.FIELD_PROFILES.equals(index)) {
					Collection<UserAccountProfile> databaseUserAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
					Collection<Profile> databaseProfiles = CollectionHelper.isEmpty(databaseUserAccountProfiles) ? null : databaseUserAccountProfiles.stream()
							.map(UserAccountProfile::getProfile).collect(Collectors.toList());
					
					__delete__(userAccount.getProfiles(), databaseUserAccountProfiles,UserAccountProfile.FIELD_PROFILE);
					__save__(UserAccountProfile.class,userAccount.getProfiles(), databaseProfiles, UserAccountProfile.FIELD_PROFILE, userAccount, UserAccountProfile.FIELD_USER_ACCOUNT);
				}else if(UserAccount.FIELD_SCOPES.equals(index)) {
					Collection<UserAccountScope> databaseUserAccountScopes = __inject__(UserAccountScopePersistence.class).readByUserAccounts(userAccount);
					Collection<Scope> databaseScopes = CollectionHelper.isEmpty(databaseUserAccountScopes) ? null : databaseUserAccountScopes.stream()
							.map(UserAccountScope::getScope).collect(Collectors.toList());
					
					__delete__(userAccount.getScopes(), databaseUserAccountScopes,UserAccountScope.FIELD_SCOPE);
					__save__(UserAccountScope.class,userAccount.getScopes(), databaseScopes, UserAccountScope.FIELD_SCOPE, userAccount, UserAccountScope.FIELD_USER_ACCOUNT);
				}else if(UserAccount.FIELD_FUNCTION_SCOPES.equals(index)) {
					Collection<UserAccountFunctionScope> databaseCollection = __inject__(UserAccountFunctionScopePersistence.class).readByUserAccount(userAccount);
					Collection<FunctionScope> databaseFunctionScopes = CollectionHelper.isEmpty(databaseCollection) ? null : databaseCollection.stream()
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
				Collection<UserAccountProfile> userAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
				if(CollectionHelper.isNotEmpty(userAccountProfiles))
					__deleteMany__(userAccountProfiles.stream().map(UserAccountProfile::getProfile).collect(Collectors.toList()));
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
