package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDtoCollection;

@Path(ScopeRepresentation.PATH)
public interface ScopeRepresentation extends RepresentationEntity<Scope,ScopeDto,ScopeDtoCollection> {
	
	String PATH = "champaction";
	
}
