package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfilePrivilegeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfilePrivilegeDtoCollection;

@Path(ProfilePrivilegeRepresentation.PATH)
public interface ProfilePrivilegeRepresentation extends RepresentationEntity<ProfilePrivilege,ProfilePrivilegeDto,ProfilePrivilegeDtoCollection> {
	
	String PATH = ProfileRepresentation.PATH+PrivilegeRepresentation.PATH;
	
}
