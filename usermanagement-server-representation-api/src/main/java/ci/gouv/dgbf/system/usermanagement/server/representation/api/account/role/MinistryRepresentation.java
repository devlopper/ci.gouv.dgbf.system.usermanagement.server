package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Ministry;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.MinistryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.MinistryDtoCollection;

@Path(MinistryRepresentation.PATH)
public interface MinistryRepresentation extends RepresentationEntity<Ministry,MinistryDto,MinistryDtoCollection> {
	
	String PATH = "/ministere";
	
}
