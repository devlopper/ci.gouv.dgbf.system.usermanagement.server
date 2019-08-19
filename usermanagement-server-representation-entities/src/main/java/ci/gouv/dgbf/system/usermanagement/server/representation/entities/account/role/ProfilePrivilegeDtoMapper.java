package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;

@Mapper(uses= {FunctionDtoCollectionMapper.class,PrivilegeDtoCollectionMapper.class})
public abstract class ProfilePrivilegeDtoMapper extends AbstractMapperSourceDestinationImpl<ProfilePrivilegeDto, ProfilePrivilege> {
	private static final long serialVersionUID = 1L;
 
}