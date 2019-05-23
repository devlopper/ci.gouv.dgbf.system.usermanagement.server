package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.AdministrativeUnitDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.AdministrativeUnitDtoCollection;

@Path(AdministrativeUnitRepresentation.PATH)
public interface AdministrativeUnitRepresentation extends RepresentationEntity<AdministrativeUnit,AdministrativeUnitDto,AdministrativeUnitDtoCollection> {
	
	String PATH = "/unite_administrative";
	
}
