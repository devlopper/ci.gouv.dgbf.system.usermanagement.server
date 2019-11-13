package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeTypeDto;

@Path(PrivilegeTypeRepresentation.PATH)
public interface PrivilegeTypeRepresentation extends RepresentationEntity<PrivilegeTypeDto> {
	
	String PATH = "type"+PrivilegeRepresentation.PATH;
	
}
