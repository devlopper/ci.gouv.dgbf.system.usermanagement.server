package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.Arrays;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.random.RandomHelper;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessServiceProvider;
import org.cyk.utility.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;

@Singleton
public class UserAccountBusinessImpl extends AbstractBusinessEntityImpl<UserAccount, UserAccountPersistence> implements UserAccountBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<UserAccount> __getPersistenceEntityClass__() {
		return UserAccount.class;
	}
	
	@Override
	public BusinessServiceProvider<UserAccount> create(UserAccount userAccount, Properties properties) {
		/*User user = userAccount.getUser();
		if(user == null)
			throw new RuntimeException("Veuillez spécifier un utilisateur.");
		String electronicMailAddress = user.getElectronicMailAddress();
		if(__inject__(StringHelper.class).isBlank(electronicMailAddress))
			throw new RuntimeException("Veuillez spécifier une addresse de courriel électronique.");
		Account account = userAccount.getAccount();
		if(account == null) {
			account = new Account();
			account.setCode(electronicMailAddress);
			account.setPass("123");
			userAccount.setAccount(account);
		}
		if(__inject__(StringHelper.class).isBlank(user.getCode()))
			user.setCode(__inject__(RandomHelper.class).getAlphabetic(4));
		__inject__(UserBusiness.class).create(user);
		
		if(__inject__(StringHelper.class).isBlank(userAccount.getCode()))
			userAccount.setCode(__inject__(RandomHelper.class).getAlphabetic(4));
		__inject__(AccountBusiness.class).create(account);
		super.create(userAccount, properties);
		
		//Notification
		__sendMail__("SIB - Création de compte utilisateur", user.getPerson().getFirstName()+" "+user.getPerson().getLastNames()
				+" , un compte utilisateur a été créé avec succès. Le nom utilisateur est : "+account.getCode()
				+" et le mot de passe est : "+account.getPass(), Arrays.asList(electronicMailAddress), Boolean.FALSE);
		*/
		return this;
	}
	
}
