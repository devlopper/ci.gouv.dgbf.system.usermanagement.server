package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserLegalPersonBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserLegalPersonPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserLegalPerson;

@Singleton
public class UserLegalPersonBusinessImpl extends AbstractBusinessEntityImpl<UserLegalPerson, UserLegalPersonPersistence> implements UserLegalPersonBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<UserLegalPerson> __getPersistenceEntityClass__() {
		return UserLegalPerson.class;
	}
	
}
