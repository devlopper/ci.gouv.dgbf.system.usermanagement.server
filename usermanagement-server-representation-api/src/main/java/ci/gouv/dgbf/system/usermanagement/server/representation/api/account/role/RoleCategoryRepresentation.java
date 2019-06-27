package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleCategoryDtoCollection;

@Path(RoleCategoryRepresentation.PATH)
public interface RoleCategoryRepresentation extends RepresentationEntity<RoleCategory,RoleCategoryDto,RoleCategoryDtoCollection> {
	
	String PATH = "/categorie";
	
}
