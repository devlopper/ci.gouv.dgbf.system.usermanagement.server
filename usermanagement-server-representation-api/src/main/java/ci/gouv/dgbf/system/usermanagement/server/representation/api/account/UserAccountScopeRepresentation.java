package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountScopeDto;

@Path(UserAccountScopeRepresentation.PATH)
public interface UserAccountScopeRepresentation extends RepresentationEntity<UserAccountScopeDto> {
	
	String PATH = UserAccountRepresentation.PATH+ScopeRepresentation.PATH;
	
}
