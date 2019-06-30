package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountFunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountFunctionScopeDtoCollection;

@Path(UserAccountFunctionScopeRepresentation.PATH)
public interface UserAccountFunctionScopeRepresentation extends RepresentationEntity<UserAccountFunctionScope,UserAccountFunctionScopeDto,UserAccountFunctionScopeDtoCollection> {
	
	String PATH = "/compteutilisateurfonctionchampaction";
	
}
