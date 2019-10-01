package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.__kernel__.object.__static__.representation.Action;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.cyk.utility.__kernel__.string.Strings;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

@Mapper(uses= {FunctionDtoCollectionMapper.class,PrivilegeDtoCollectionMapper.class})
public abstract class ProfileDtoMapper extends AbstractMapperSourceDestinationImpl<ProfileDto, Profile> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Strings __getActionsIdentifiers__(Profile destination, ProfileDto source) {
		Strings strings = super.__getActionsIdentifiers__(destination, source);
		if(strings != null)
			strings.remove(Action.IDENTIFIER_DELETE);
		return strings; 
	}
	
}