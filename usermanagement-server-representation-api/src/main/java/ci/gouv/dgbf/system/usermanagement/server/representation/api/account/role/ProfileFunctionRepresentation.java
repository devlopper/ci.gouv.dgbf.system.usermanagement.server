package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileFunctionDtoCollection;

@Path(ProfileFunctionRepresentation.PATH)
public interface ProfileFunctionRepresentation extends RepresentationEntity<ProfileFunction,ProfileFunctionDto,ProfileFunctionDtoCollection> {
	
	String PATH = ProfileRepresentation.PATH+FunctionRepresentation.PATH;
	
}
