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

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountRolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountRolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountRolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;

@ApplicationScoped
public class UserAccountBusinessImpl extends AbstractBusinessEntityImpl<UserAccount, UserAccountPersistence> implements UserAccountBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<UserAccount> __getPersistenceEntityClass__() {
		return UserAccount.class;
	}
	
	@Override
	protected void __listenExecuteCreateOneBefore__(UserAccount userAccount, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateOneBefore__(userAccount, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				__create__(userAccount.getUser());
				__create__(userAccount.getAccount());
			}
		});
	}
	
	@Override
	protected void __listenExecuteCreateOneAfter__(UserAccount userAccount, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateOneAfter__(userAccount, properties, function);
		if(Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(userAccount.getProfiles()))) {
			Collection<UserAccountProfile> userAccountProfiles = new ArrayList<>();
			for(Profile index : userAccount.getProfiles().get())
				userAccountProfiles.add(new UserAccountProfile().setUserAccount(userAccount).setProfile(index));
			__inject__(UserAccountProfileBusiness.class).createMany(userAccountProfiles);
		}
		
		if(Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(userAccount.getPostes()))) {
			Collection<UserAccountRolePoste> userAccountRolePostes = new ArrayList<>();
			for(RolePoste index : userAccount.getPostes().get())
				userAccountRolePostes.add(new UserAccountRolePoste().setUserAccount(userAccount).setRolePoste(index));
			__inject__(UserAccountRolePosteBusiness.class).createMany(userAccountRolePostes);
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
					}else if(UserAccount.FIELD_POSTES.equals(field)) {
						Collection<UserAccountRolePoste> userAccountRolePostes = __inject__(UserAccountRolePostePersistence.class).readByUserAccount(userAccount);
						if(__injectCollectionHelper__().isNotEmpty(userAccountRolePostes))
							userAccount.getPostes(Boolean.TRUE).add(userAccountRolePostes.stream().map(UserAccountRolePoste::getRolePoste).collect(Collectors.toList()));
					}
				}
			});
	}
	
	@Override
	protected void __listenExecuteUpdateOneBefore__(UserAccount userAccount, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateOneBefore__(userAccount, properties, function);
		__inject__(UserBusiness.class).save(userAccount.getUser());
		__inject__(AccountBusiness.class).save(userAccount.getAccount());
		
		Collection<UserAccountRolePoste> databaseCollection = __inject__(UserAccountRolePostePersistence.class).readByUserAccount(userAccount);
		Collection<RolePoste> databaseRolePostes = __injectCollectionHelper__().isEmpty(databaseCollection) ? null : databaseCollection.stream()
				.map(UserAccountRolePoste::getRolePoste).collect(Collectors.toList());
		
		__delete__(userAccount.getPostes(), databaseCollection,UserAccountRolePoste.FIELD_ROLE_POSTE);
		__save__(UserAccountRolePoste.class,userAccount.getPostes(), databaseRolePostes, UserAccountRolePoste.FIELD_ROLE_POSTE, userAccount, UserAccountRolePoste.FIELD_USER_ACCOUNT);
		
		Collection<UserAccountProfile> databaseUserAccountProfiles = __inject__(UserAccountProfilePersistence.class).readByUserAccount(userAccount);
		Collection<Profile> databaseProfiles = __injectCollectionHelper__().isEmpty(databaseCollection) ? null : databaseUserAccountProfiles.stream()
				.map(UserAccountProfile::getProfile).collect(Collectors.toList());
		
		__delete__(userAccount.getProfiles(), databaseUserAccountProfiles,UserAccountProfile.FIELD_PROFILE);
		__save__(UserAccountProfile.class,userAccount.getProfiles(), databaseProfiles, UserAccountProfile.FIELD_PROFILE, userAccount, UserAccountProfile.FIELD_USER_ACCOUNT);
	}
	
	@Override
	protected void __listenExecuteDeleteOneBefore__(UserAccount userAccount, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteOneBefore__(userAccount, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				__deleteMany__(__inject__(UserAccountRolePostePersistence.class).readByUserAccount(userAccount));
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

}
