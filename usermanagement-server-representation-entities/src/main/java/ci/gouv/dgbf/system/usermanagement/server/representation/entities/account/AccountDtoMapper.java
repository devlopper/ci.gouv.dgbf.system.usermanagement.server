package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

@Mapper
public abstract class AccountDtoMapper extends AbstractMapperSourceDestinationImpl<AccountDto, Account> {
	private static final long serialVersionUID = 1L;
    
 
}