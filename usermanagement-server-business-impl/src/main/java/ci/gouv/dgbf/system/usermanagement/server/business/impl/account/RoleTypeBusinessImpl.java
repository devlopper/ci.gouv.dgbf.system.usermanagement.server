package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;

@Singleton
public class RoleTypeBusinessImpl extends AbstractBusinessEntityImpl<RoleType, RoleTypePersistence> implements RoleTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<RoleType> __getPersistenceEntityClass__() {
		return RoleType.class;
	}
	
}
