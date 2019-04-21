package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDtoCollection;

@Path(RoleFunctionRepresentation.PATH)
public interface RoleFunctionRepresentation extends RepresentationEntity<RoleFunction,RoleFunctionDto,RoleFunctionDtoCollection> {
	
	String PATH = "/rolefunction";
	
}
