package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimDtoCollection;

@Path(UserAccountInterimRepresentation.PATH)
public interface UserAccountInterimRepresentation extends RepresentationEntity<UserAccountInterim,UserAccountInterimDto,UserAccountInterimDtoCollection> {
	
	String PATH = "/interim";
	
}
