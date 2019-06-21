package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountInterimModelBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountInterimModelPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterimModel;

@ApplicationScoped
public class UserAccountInterimModelBusinessImpl extends AbstractBusinessEntityImpl<UserAccountInterimModel, UserAccountInterimModelPersistence> implements UserAccountInterimModelBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<UserAccountInterimModel> __getPersistenceEntityClass__() {
		return UserAccountInterimModel.class;
	}
	
}
