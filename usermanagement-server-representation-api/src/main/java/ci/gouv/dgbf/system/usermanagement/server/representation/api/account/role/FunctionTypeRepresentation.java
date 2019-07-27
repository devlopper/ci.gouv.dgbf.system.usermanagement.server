package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionTypeDtoCollection;

@Path(FunctionTypeRepresentation.PATH)
public interface FunctionTypeRepresentation extends RepresentationEntity<FunctionType,FunctionTypeDto,FunctionTypeDtoCollection> {
	
	String PATH = "type"+FunctionRepresentation.PATH;
	
}
