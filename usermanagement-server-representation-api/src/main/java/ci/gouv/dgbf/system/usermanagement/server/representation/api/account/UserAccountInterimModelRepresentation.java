package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimModelDto;

@Path(UserAccountInterimModelRepresentation.PATH)
public interface UserAccountInterimModelRepresentation extends RepresentationEntity<UserAccountInterimModelDto> {
	
	String PATH = "/interimaire";
	
}
