package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.cyk.utility.__kernel__.constant.ConstantCharacter;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;
import org.cyk.utility.server.business.BusinessFunctionRemover;
import org.cyk.utility.string.Strings;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountRolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountRolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountRolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;

@Singleton
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
				__createIfSystemIdentifierIsBlank__(userAccount.getUser());//TODO write save method and use it here
				__create__(userAccount.getAccount());//TODO write save method and use it here
			}
		});
	}
	
	@Override
	protected void __listenExecuteCreateOneAfter__(UserAccount userAccount, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateOneAfter__(userAccount, properties, function);
		if(Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(userAccount.getRolePostes()))) {
			Collection<UserAccountRolePoste> userAccountRolePostes = new ArrayList<>();
			for(RolePoste index : userAccount.getRolePostes().get())
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
		Object fieldsObject = Properties.getFromPath(properties, Properties.FIELDS);
		Strings fields = null;
		if(fieldsObject instanceof Strings)
			fields = (Strings) fieldsObject;
		else if(fieldsObject instanceof String) {
			fields = __inject__(Strings.class).add(StringUtils.split((String) fieldsObject,ConstantCharacter.COMA.toString()));
		}
		
		if(__injectCollectionHelper__().isNotEmpty(fields))
			fields.get().forEach(new Consumer<String>() {
				@Override
				public void accept(String field) {
					if(UserAccount.FIELD_ROLE_POSTES.equals(field)) {
						Collection<UserAccountRolePoste> userAccountRolePostes = __inject__(UserAccountRolePostePersistence.class).readByUserAccount(userAccount);
						if(__injectCollectionHelper__().isNotEmpty(userAccountRolePostes))
							userAccount.getRolePostes(Boolean.TRUE).add(userAccountRolePostes.stream().map(UserAccountRolePoste::getRolePoste).collect(Collectors.toList()));
					}
				}
			});
	}
	
	@Override
	protected void __listenExecuteUpdateOneBefore__(UserAccount userAccount, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateOneBefore__(userAccount, properties, function);
		__inject__(UserBusiness.class).update(userAccount.getUser());//TODO write save method and use it here
		__inject__(AccountBusiness.class).update(userAccount.getAccount());//TODO write save method and use it here
		
		//Collection<RolePoste> userCollection = userAccount.getRolePostes();
		Collection<UserAccountRolePoste> databaseCollection = __inject__(UserAccountRolePostePersistence.class).readByUserAccount(userAccount);
		Collection<RolePoste> databaseRolePostes = __injectCollectionHelper__().isEmpty(databaseCollection) ? null : databaseCollection.stream()
				.map(UserAccountRolePoste::getRolePoste).collect(Collectors.toList());
		Collection<UserAccountRolePoste> userAccountRolePostesToDelete = null;
		Collection<UserAccountRolePoste> userAccountRolePostesToSave = null;
		
		//what to delete
		if(__injectCollectionHelper__().isNotEmpty(databaseCollection))
			for(UserAccountRolePoste database : databaseCollection) {
				if(!Boolean.TRUE.equals(__injectCollectionHelper__().contains(userAccount.getRolePostes(), database.getRolePoste()))) {
					if(userAccountRolePostesToDelete == null)
						userAccountRolePostesToDelete = new ArrayList<>();
					userAccountRolePostesToDelete.add(database);
				}
			}
		
		//what to save
		if(__injectCollectionHelper__().isNotEmpty(userAccount.getRolePostes())) {
			for(RolePoste index : userAccount.getRolePostes().get()) {
				//check if not yet created
				if(!Boolean.TRUE.equals(__injectCollectionHelper__().contains(databaseRolePostes, index))) {
					if(userAccountRolePostesToSave == null)
						userAccountRolePostesToSave = new ArrayList<>();
					userAccountRolePostesToSave.add(new UserAccountRolePoste().setUserAccount(userAccount).setRolePoste(index));	
				}
			}
		}
		
		if(__injectCollectionHelper__().isNotEmpty(userAccountRolePostesToDelete))
			__inject__(UserAccountRolePosteBusiness.class).deleteMany(userAccountRolePostesToDelete);
		if(__injectCollectionHelper__().isNotEmpty(userAccountRolePostesToSave))
			__inject__(UserAccountRolePosteBusiness.class).createMany(userAccountRolePostesToSave);
	}
	
	@Override
	protected void __listenExecuteDeleteOneBefore__(UserAccount userAccount, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteOneBefore__(userAccount, properties, function);
		if(userAccount.getUser() != null)
			__inject__(UserBusiness.class).delete(userAccount.getUser());
		if(userAccount.getAccount() != null)
			__inject__(AccountBusiness.class).delete(userAccount.getAccount());
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				Collection<UserAccountRolePoste> userAccountRolePostes = __inject__(UserAccountRolePostePersistence.class).readByUserAccount(userAccount);
				if(__injectCollectionHelper__().isNotEmpty(userAccountRolePostes))
					__inject__(UserAccountRolePosteBusiness.class).deleteMany(userAccountRolePostes);
			}
		});
	}
	
}
