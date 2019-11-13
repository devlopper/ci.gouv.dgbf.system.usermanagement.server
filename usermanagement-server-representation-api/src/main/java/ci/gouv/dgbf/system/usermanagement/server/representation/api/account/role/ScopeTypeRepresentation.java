package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeDto;

@Path(ScopeTypeRepresentation.PATH)
public interface ScopeTypeRepresentation extends RepresentationEntity<ScopeTypeDto> {
	
	String PATH = "type"+ScopeRepresentation.PATH;
	
}
