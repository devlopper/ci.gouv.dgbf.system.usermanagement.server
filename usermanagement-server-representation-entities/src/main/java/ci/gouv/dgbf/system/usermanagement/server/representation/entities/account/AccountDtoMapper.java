package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.mapping.MapperSourceDestination;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

@Mapper
public interface AccountDtoMapper extends MapperSourceDestination<AccountDto, Account> {
    
 
}