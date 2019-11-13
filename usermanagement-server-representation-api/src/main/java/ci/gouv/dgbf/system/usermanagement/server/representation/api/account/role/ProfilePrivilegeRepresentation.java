package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfilePrivilegeDto;

@Path(ProfilePrivilegeRepresentation.PATH)
public interface ProfilePrivilegeRepresentation extends RepresentationEntity<ProfilePrivilegeDto> {
	
	String PATH = ProfileRepresentation.PATH+PrivilegeRepresentation.PATH;
	
}
