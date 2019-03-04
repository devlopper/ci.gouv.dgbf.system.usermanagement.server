package ci.gouv.dgbf.system.usermanagement.server.business.api.account;

import java.io.Serializable;

import org.cyk.utility.__kernel__.function.Function;
import org.cyk.utility.assertion.AbstractAssertionsProviderForImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;

public class UserAccountAssertionsProviderImpl extends AbstractAssertionsProviderForImpl<UserAccount> implements UserAccountAssertionsProvider,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void ____execute____(Function<?,?> function,Object filter, UserAccount userAccount) {
		if(function instanceof BusinessFunctionCreator) {
			if(userAccount.getUser() == null)
				throw new RuntimeException("Veuillez spécifier un utilisateur.");				
		}else {
			
		}
	}
	
}