package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationDtoCollection;

@Path(PosteLocationRepresentation.PATH)
public interface PosteLocationRepresentation extends RepresentationEntity<PosteLocation,PosteLocationDto,PosteLocationDtoCollection> {
	
	String PATH = "/structure";
	
}
