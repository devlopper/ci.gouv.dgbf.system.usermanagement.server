package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollection;

@Path(FunctionScopeRepresentation.PATH)
public interface FunctionScopeRepresentation extends RepresentationEntity<FunctionScope,FunctionScopeDto,FunctionScopeDtoCollection> {
	
	String PATH = "/fonctionchampaction";
	
}
