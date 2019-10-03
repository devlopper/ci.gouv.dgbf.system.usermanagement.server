package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterimModel;

@Mapper
public abstract class UserAccountInterimModelDtoMapper extends AbstractMapperSourceDestinationImpl<UserAccountInterimModelDto, UserAccountInterimModel> {
	private static final long serialVersionUID = 1L;
 
}