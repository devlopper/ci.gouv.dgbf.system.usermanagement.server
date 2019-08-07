package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserFunctionDtoCollection;

@Path(UserFunctionRepresentation.PATH)
public interface UserFunctionRepresentation extends RepresentationEntity<UserFunction,UserFunctionDto,UserFunctionDtoCollection> {
	
	String PATH = UserRepresentation.PATH+FunctionRepresentation.PATH;
	
}
