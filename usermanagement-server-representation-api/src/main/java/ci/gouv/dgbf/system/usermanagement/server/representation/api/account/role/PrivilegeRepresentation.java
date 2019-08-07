package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeDtoCollection;

@Path(PrivilegeRepresentation.PATH)
public interface PrivilegeRepresentation extends RepresentationEntity<Privilege,PrivilegeDto,PrivilegeDtoCollection> {
	
	String PATH = "privilege";
	
}
