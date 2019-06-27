package ci.gouv.dgbf.system.usermanagement.server.business.api.account;

import java.util.Collection;

import org.cyk.utility.server.business.BusinessEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;

public interface UserAccountProfileBusiness extends BusinessEntity<UserAccountProfile> {

	Collection<UserAccountProfile> findByUserAccount(UserAccount userAccount);
	
}
