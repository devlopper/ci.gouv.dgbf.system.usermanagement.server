package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.MinistryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.MinistryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Ministry;

@Singleton
public class MinistryBusinessImpl extends AbstractBusinessEntityImpl<Ministry, MinistryPersistence> implements MinistryBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Ministry> __getPersistenceEntityClass__() {
		return Ministry.class;
	}
	
}
