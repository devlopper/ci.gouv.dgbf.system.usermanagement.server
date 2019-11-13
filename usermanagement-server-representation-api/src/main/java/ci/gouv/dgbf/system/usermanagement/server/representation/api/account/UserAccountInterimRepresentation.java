package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimDto;

@Path(UserAccountInterimRepresentation.PATH)
public interface UserAccountInterimRepresentation extends RepresentationEntity<UserAccountInterimDto> {
	
	String PATH = "/interim";
	
}
