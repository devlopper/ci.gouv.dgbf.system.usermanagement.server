package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleDtoCollection;

@Path(RoleRepresentation.PATH)
public interface RoleRepresentation extends RepresentationEntity<Role,RoleDto,RoleDtoCollection> {
	
	String PATH = "/role";
	
}
