package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterimModel;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollectionMapper;

@Mapper(uses= {FunctionDtoCollectionMapper.class,ProfileDtoCollectionMapper.class,FunctionScopeDtoCollectionMapper.class})
public abstract class UserAccountInterimModelDtoMapper extends AbstractMapperSourceDestinationImpl<UserAccountInterimModelDto, UserAccountInterimModel> {
	private static final long serialVersionUID = 1L;
 
}