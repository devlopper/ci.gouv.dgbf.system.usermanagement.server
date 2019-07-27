package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

@Mapper(uses= {FunctionDtoCollectionMapper.class,PrivilegeDtoCollectionMapper.class})
public abstract class ProfileDtoMapper extends AbstractMapperSourceDestinationImpl<ProfileDto, Profile> {
	private static final long serialVersionUID = 1L;
	
}