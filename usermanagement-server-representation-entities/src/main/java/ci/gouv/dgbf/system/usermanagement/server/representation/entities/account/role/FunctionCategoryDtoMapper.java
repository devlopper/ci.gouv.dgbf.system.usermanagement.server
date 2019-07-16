package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.mapping.MapperSourceDestination;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;

@Mapper
public interface FunctionCategoryDtoMapper extends MapperSourceDestination<FunctionCategoryDto, FunctionCategory> {
    
 
}