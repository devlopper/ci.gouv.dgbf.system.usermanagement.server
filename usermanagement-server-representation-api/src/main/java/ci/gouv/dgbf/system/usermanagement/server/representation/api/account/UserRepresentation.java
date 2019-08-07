package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDtoCollection;

@Path(UserRepresentation.PATH)
public interface UserRepresentation extends RepresentationEntity<User,UserDto,UserDtoCollection> {
	
	String PATH = "utilisateur";
	
}
