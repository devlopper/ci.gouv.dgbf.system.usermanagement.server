package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeFunctionDto;

@Path(ScopeTypeFunctionRepresentation.PATH)
public interface ScopeTypeFunctionRepresentation extends RepresentationEntity<ScopeTypeFunctionDto> {
	
	String PATH = ProfileRepresentation.PATH+ScopeTypeRepresentation.PATH;
	
}
