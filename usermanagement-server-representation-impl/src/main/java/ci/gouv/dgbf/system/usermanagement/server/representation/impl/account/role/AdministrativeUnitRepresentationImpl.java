package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.AdministrativeUnitBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.AdministrativeUnitRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.AdministrativeUnitDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.AdministrativeUnitDtoCollection;

@Singleton
public class AdministrativeUnitRepresentationImpl extends AbstractRepresentationEntityImpl<AdministrativeUnit,AdministrativeUnitBusiness,AdministrativeUnitDto,AdministrativeUnitDtoCollection> implements AdministrativeUnitRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<AdministrativeUnit> getPersistenceEntityClass() {
		return AdministrativeUnit.class;
	}
	
}
