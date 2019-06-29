package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollection;

@Path(FunctionRepresentation.PATH)
public interface FunctionRepresentation extends RepresentationEntity<Function,FunctionDto,FunctionDtoCollection> {
	
	String PATH = "/fonction";
	
}
