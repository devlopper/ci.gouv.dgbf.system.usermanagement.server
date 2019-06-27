package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileRoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileRoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileRoleFunctionDtoCollection;

@Path(ProfileRoleFunctionRepresentation.PATH)
public interface ProfileRoleFunctionRepresentation extends RepresentationEntity<ProfileRoleFunction,ProfileRoleFunctionDto,ProfileRoleFunctionDtoCollection> {
	
	String PATH = "/profilefonction";
	
}
