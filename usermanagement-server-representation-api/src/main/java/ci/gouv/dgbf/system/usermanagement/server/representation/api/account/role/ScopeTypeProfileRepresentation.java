package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeProfileDto;

@Path(ScopeTypeProfileRepresentation.PATH)
public interface ScopeTypeProfileRepresentation extends RepresentationEntity<ScopeTypeProfileDto> {
	
	String PATH = ProfileRepresentation.PATH+ScopeTypeRepresentation.PATH;
	
}
