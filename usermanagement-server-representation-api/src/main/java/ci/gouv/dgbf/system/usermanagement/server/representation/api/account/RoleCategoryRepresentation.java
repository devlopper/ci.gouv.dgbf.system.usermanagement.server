package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDtoCollection;

@Path(RoleCategoryRepresentation.PATH)
public interface RoleCategoryRepresentation extends RepresentationEntity<RoleCategory,RoleCategoryDto,RoleCategoryDtoCollection> {
	
	String PATH = "/rolecategory";
	
}
