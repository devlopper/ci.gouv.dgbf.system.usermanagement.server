package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeDtoCollection;

@Path(ScopeTypeRepresentation.PATH)
public interface ScopeTypeRepresentation extends RepresentationEntity<ScopeType,ScopeTypeDto,ScopeTypeDtoCollection> {
	
	String PATH = "/typestructure";
	
}
