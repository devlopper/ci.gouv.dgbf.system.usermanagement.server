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
import org.cyk.utility.server.business.BusinessFunctionRemover;
import org.cyk.utility.string.Strings;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountRolePosteBusiness;
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
				__createIfSystemIdentifierIsBlank__(userAccount.getUser());
				__create__(userAccount.getAccount());
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
	protected void __listenExecuteDeleteOneBefore__(UserAccount userAccount, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteOneBefore__(userAccount, properties, function);
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
