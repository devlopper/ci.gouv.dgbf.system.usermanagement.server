package ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionCategoryDtoCollection;

@Path(FunctionCategoryRepresentation.PATH)
public interface FunctionCategoryRepresentation extends RepresentationEntity<FunctionCategory,FunctionCategoryDto,FunctionCategoryDtoCollection> {
	
	String PATH = "/categorie";
	
}
