package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleTypeDtoCollection;

@Path(RoleTypeRepresentation.PATH)
public interface RoleTypeRepresentation extends RepresentationEntity<RoleType,RoleTypeDto,RoleTypeDtoCollection> {
	
	String PATH = "/roletype";
	
}
