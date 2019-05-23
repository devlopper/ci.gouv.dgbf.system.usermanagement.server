package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProgramDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProgramDtoCollection;

@Path(ProgramRepresentation.PATH)
public interface ProgramRepresentation extends RepresentationEntity<Program,ProgramDto,ProgramDtoCollection> {
	
	String PATH = "/programme";
	
}
