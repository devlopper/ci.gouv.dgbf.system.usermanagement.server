package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;

@Mapper
public abstract class UserFunctionDtoMapper extends AbstractMapperSourceDestinationImpl<UserFunctionDto, UserFunction> {
	private static final long serialVersionUID = 1L;
 
}