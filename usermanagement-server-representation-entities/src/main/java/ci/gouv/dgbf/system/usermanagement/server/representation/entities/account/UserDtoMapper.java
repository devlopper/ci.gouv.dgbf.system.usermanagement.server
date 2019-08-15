package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollectionMapper;

@Mapper(uses= {FunctionDtoCollectionMapper.class})
public abstract class UserDtoMapper extends AbstractMapperSourceDestinationImpl<UserDto, User> {
	private static final long serialVersionUID = 1L;
 
}