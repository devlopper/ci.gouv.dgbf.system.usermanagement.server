package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionTypeDto;

@Path(FunctionTypeRepresentation.PATH)
public interface FunctionTypeRepresentation extends RepresentationEntity<FunctionTypeDto> {
	
	String PATH = "type"+FunctionRepresentation.PATH;
	
}
