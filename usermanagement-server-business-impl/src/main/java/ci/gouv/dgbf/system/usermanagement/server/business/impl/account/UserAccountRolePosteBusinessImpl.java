package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountRolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountRolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountRolePoste;

@Singleton
public class UserAccountRolePosteBusinessImpl extends AbstractBusinessEntityImpl<UserAccountRolePoste, UserAccountRolePostePersistence> implements UserAccountRolePosteBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<UserAccountRolePoste> findByUserAccount(UserAccount userAccount) {
		return getPersistence().readByUserAccount(userAccount);
	}
	
	@Override
	protected Class<UserAccountRolePoste> __getPersistenceEntityClass__() {
		return UserAccountRolePoste.class;
	}
	
}
