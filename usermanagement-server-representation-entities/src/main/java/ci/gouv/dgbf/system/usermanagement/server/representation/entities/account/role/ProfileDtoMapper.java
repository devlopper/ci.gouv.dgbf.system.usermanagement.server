package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.util.Collection;

import org.cyk.utility.__kernel__.instance.InstanceHelper;
import org.cyk.utility.__kernel__.object.__static__.representation.Action;
import org.cyk.utility.__kernel__.string.Strings;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

@Mapper
public abstract class ProfileDtoMapper extends AbstractMapperSourceDestinationImpl<ProfileDto, Profile> {
	private static final long serialVersionUID = 1L;
	
	public Collection<Function> getFuntions(Collection<FunctionDto> dtos) {
    	return InstanceHelper.getIdentifiedFromIdentifiers(Function.class, dtos);
    }
	
	public Collection<Privilege> getPrivileges(Collection<PrivilegeDto> dtos) {
    	return InstanceHelper.getIdentifiedFromIdentifiers(Privilege.class, dtos);
    }
	
	@Override
	protected Strings __getActionsIdentifiers__(Profile destination, ProfileDto source) {
		Strings strings = super.__getActionsIdentifiers__(destination, source);
		if(strings != null)
			strings.remove(Action.IDENTIFIER_DELETE);
		return strings; 
	}
	
}