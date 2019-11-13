package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileTypeDto;

@Path(ProfileTypeRepresentation.PATH)
public interface ProfileTypeRepresentation extends RepresentationEntity<ProfileTypeDto> {
	
	String PATH = "type"+ProfileRepresentation.PATH;
	
}
