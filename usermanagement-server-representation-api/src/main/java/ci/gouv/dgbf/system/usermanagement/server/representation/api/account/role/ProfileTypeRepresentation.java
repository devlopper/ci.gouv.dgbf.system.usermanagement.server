package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileTypeDtoCollection;

@Path(ProfileTypeRepresentation.PATH)
public interface ProfileTypeRepresentation extends RepresentationEntity<ProfileType,ProfileTypeDto,ProfileTypeDtoCollection> {
	
	String PATH = "type"+ProfileRepresentation.PATH;
	
}
