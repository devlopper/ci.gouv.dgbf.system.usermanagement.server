package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.mapping.MapperSourceDestination;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollectionMapper;

@Mapper(uses= {FunctionDtoCollectionMapper.class,ProfileDtoCollectionMapper.class,FunctionScopeDtoCollectionMapper.class})
public interface UserAccountDtoMapper extends MapperSourceDestination<UserAccountDto, UserAccount> {
    
 
}