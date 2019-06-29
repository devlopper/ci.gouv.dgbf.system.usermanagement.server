package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollection;

@Path(ProfileRepresentation.PATH)
public interface ProfileRepresentation extends RepresentationEntity<Profile,ProfileDto,ProfileDtoCollection> {
	
	
	
	String PATH = "/profile";
	
}
