package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeHierarchyDto;

@Path(PrivilegeHierarchyRepresentation.PATH)
public interface PrivilegeHierarchyRepresentation extends RepresentationEntity<PrivilegeHierarchyDto> {
	
	String PATH = "privilegehierachie";
	
}
