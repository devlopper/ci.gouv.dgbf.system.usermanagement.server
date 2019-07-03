package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollection;

@Path(ProfileRepresentation.PATH)
public interface ProfileRepresentation extends RepresentationEntity<Profile,ProfileDto,ProfileDtoCollection> {
	
	@POST
	@Path("/__internal__/data/export")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	Response exportToKeycloak();
	
	String PATH = "profile";
	
}
