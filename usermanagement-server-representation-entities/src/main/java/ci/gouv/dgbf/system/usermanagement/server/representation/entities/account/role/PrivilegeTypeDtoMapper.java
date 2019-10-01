package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;

@Mapper
public abstract class PrivilegeTypeDtoMapper extends AbstractMapperSourceDestinationImpl<PrivilegeTypeDto, PrivilegeType> {
	private static final long serialVersionUID = 1L;
  
 
}