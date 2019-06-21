package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountInterimBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountInterimPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;

@ApplicationScoped
public class UserAccountInterimBusinessImpl extends AbstractBusinessEntityImpl<UserAccountInterim, UserAccountInterimPersistence> implements UserAccountInterimBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<UserAccountInterim> __getPersistenceEntityClass__() {
		return UserAccountInterim.class;
	}
	
	

}
