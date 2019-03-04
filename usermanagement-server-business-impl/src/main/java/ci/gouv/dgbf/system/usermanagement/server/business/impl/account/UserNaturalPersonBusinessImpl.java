package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserNaturalPersonBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserNaturalPersonPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserNaturalPerson;

@Singleton
public class UserNaturalPersonBusinessImpl extends AbstractBusinessEntityImpl<UserNaturalPerson, UserNaturalPersonPersistence> implements UserNaturalPersonBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<UserNaturalPerson> __getPersistenceEntityClass__() {
		return UserNaturalPerson.class;
	}
	
}
