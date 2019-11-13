package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileFunctionDto;

@Path(ProfileFunctionRepresentation.PATH)
public interface ProfileFunctionRepresentation extends RepresentationEntity<ProfileFunctionDto> {
	
	String PATH = ProfileRepresentation.PATH+FunctionRepresentation.PATH;
	
}
