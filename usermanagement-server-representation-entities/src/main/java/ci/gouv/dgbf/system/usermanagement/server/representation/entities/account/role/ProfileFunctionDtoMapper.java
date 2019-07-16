package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.mapping.MapperSourceDestination;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

@Mapper(uses= {FunctionDtoCollectionMapper.class})
public interface ProfileFunctionDtoMapper extends MapperSourceDestination<ProfileFunctionDto, ProfileFunction> {
    
 
}