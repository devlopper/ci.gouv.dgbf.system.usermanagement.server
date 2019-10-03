package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.__kernel__.instance.InstanceHelper;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;

@Mapper
public abstract class UserAccountDtoMapper extends AbstractMapperSourceDestinationImpl<UserAccountDto, UserAccount> {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenGetSourceAfter__(UserAccount destination, UserAccountDto source) {
		super.__listenGetSourceAfter__(destination, source);
		if(source.getUser() != null)
			source.setFunctions(source.getUser().getFunctions());
	}
	
	@Override
	protected void __listenGetDestinationAfter__(UserAccountDto source, UserAccount destination) {
		super.__listenGetDestinationAfter__(source, destination);
		destination.setFunctions(InstanceHelper.getIdentifiedFromIdentifiers(Function.class, destination.getFunctions()));
		if(destination.getUser() != null)
			destination.getUser().setFunctions(destination.getFunctions());
	}
	
	
}