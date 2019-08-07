package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeTypeDtoCollection;

@Path(PrivilegeTypeRepresentation.PATH)
public interface PrivilegeTypeRepresentation extends RepresentationEntity<PrivilegeType,PrivilegeTypeDto,PrivilegeTypeDtoCollection> {
	
	String PATH = "type"+PrivilegeRepresentation.PATH;
	
}
