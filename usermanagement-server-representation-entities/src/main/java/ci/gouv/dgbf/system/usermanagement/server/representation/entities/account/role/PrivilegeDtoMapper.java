package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;

@Mapper(uses= {PrivilegeTypeDtoMapper.class})
public abstract class PrivilegeDtoMapper extends AbstractMapperSourceDestinationImpl<PrivilegeDto, Privilege> {
	private static final long serialVersionUID = 1L;
    
}