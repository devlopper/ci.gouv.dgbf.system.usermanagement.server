package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocationType;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationTypeDtoCollection;

@Path(PosteLocationTypeRepresentation.PATH)
public interface PosteLocationTypeRepresentation extends RepresentationEntity<PosteLocationType,PosteLocationTypeDto,PosteLocationTypeDtoCollection> {
	
	String PATH = "/typestructure";
	
}
