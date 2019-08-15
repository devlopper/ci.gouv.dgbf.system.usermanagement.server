package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;

@Mapper
public abstract class ServiceDtoMapper extends AbstractMapperSourceDestinationImpl<ServiceDto, Service> {
	private static final long serialVersionUID = 1L;
 
}