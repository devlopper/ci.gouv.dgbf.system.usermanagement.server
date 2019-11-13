package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;

@Path(UserRepresentation.PATH)
public interface UserRepresentation extends RepresentationEntity<UserDto> {
	
	String PATH = "utilisateur";
	
}
