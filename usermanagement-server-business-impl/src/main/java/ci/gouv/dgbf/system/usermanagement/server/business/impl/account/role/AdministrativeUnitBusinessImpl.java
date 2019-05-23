package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.AdministrativeUnitBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.AdministrativeUnitPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;

@Singleton
public class AdministrativeUnitBusinessImpl extends AbstractBusinessEntityImpl<AdministrativeUnit, AdministrativeUnitPersistence> implements AdministrativeUnitBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<AdministrativeUnit> __getPersistenceEntityClass__() {
		return AdministrativeUnit.class;
	}
	
}
