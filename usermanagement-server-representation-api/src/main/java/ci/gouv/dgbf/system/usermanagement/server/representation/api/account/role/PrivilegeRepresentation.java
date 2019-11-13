package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeDto;

@Path(PrivilegeRepresentation.PATH)
public interface PrivilegeRepresentation extends RepresentationEntity<PrivilegeDto> {
	
	String PATH = "privilege";
	
}
