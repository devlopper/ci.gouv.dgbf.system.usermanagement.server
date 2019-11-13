package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;

@Path(ScopeRepresentation.PATH)
public interface ScopeRepresentation extends RepresentationEntity<ScopeDto> {
	
	String PATH = "champaction";
	
}
