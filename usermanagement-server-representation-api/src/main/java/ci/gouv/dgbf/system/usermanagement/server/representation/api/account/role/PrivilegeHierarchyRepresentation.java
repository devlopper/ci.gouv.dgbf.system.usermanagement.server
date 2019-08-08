package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeHierarchyDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeHierarchyDtoCollection;

@Path(PrivilegeHierarchyRepresentation.PATH)
public interface PrivilegeHierarchyRepresentation extends RepresentationEntity<PrivilegeHierarchy,PrivilegeHierarchyDto,PrivilegeHierarchyDtoCollection> {
	
	String PATH = "privilegehierachie";
	
}
