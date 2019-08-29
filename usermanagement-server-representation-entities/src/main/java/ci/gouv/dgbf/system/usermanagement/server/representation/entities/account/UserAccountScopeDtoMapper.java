package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDtoCollectionMapper;

@Mapper(uses= {FunctionDtoCollectionMapper.class,ProfileDtoCollectionMapper.class,FunctionScopeDtoCollectionMapper.class
		,ScopeDtoCollectionMapper.class})
public abstract class UserAccountScopeDtoMapper extends AbstractMapperSourceDestinationImpl<UserAccountScopeDto, UserAccountScope> {
	private static final long serialVersionUID = 1L;
 
}