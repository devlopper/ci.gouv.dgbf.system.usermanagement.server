package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;

@ApplicationScoped
public class UserAccountProfileBusinessImpl extends AbstractBusinessEntityImpl<UserAccountProfile, UserAccountProfilePersistence> implements UserAccountProfileBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<UserAccountProfile> findByUserAccount(UserAccount userAccount) {
		return getPersistence().readByUserAccount(userAccount);
	}
	

}
