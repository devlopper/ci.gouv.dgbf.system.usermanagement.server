package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

@Mapper(uses= {FunctionDtoCollectionMapper.class})
public abstract class ProfileFunctionDtoMapper extends AbstractMapperSourceDestinationImpl<ProfileFunctionDto, ProfileFunction> {
	private static final long serialVersionUID = 1L;
 
}