package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;

@Path(FunctionRepresentation.PATH)
public interface FunctionRepresentation extends RepresentationEntity<FunctionDto> {
	
	String PATH = "fonction";
	
}
