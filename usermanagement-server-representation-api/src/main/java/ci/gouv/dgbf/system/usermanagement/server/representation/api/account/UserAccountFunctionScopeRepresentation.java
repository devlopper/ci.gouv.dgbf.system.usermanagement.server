package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountFunctionScopeDto;

@Path(UserAccountFunctionScopeRepresentation.PATH)
public interface UserAccountFunctionScopeRepresentation extends RepresentationEntity<UserAccountFunctionScopeDto> {
	
	String PATH = "/compteutilisateurfonctionchampaction";
	
}
