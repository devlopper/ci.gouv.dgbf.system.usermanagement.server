package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountScopeDtoCollection;

@Path(UserAccountScopeRepresentation.PATH)
public interface UserAccountScopeRepresentation extends RepresentationEntity<UserAccountScope,UserAccountScopeDto,UserAccountScopeDtoCollection> {
	
	String PATH = UserAccountRepresentation.PATH+ScopeRepresentation.PATH;
	
}
