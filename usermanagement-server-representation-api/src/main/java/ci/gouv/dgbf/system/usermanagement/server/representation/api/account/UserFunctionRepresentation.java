package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserFunctionDto;

@Path(UserFunctionRepresentation.PATH)
public interface UserFunctionRepresentation extends RepresentationEntity<UserFunctionDto> {
	
	String PATH = UserRepresentation.PATH+FunctionRepresentation.PATH;
	
}
