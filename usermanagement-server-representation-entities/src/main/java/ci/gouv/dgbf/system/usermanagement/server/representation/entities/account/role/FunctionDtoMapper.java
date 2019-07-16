package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;

@Mapper
public abstract class FunctionDtoMapper extends AbstractMapperSourceDestinationImpl<FunctionDto, Function> {
	private static final long serialVersionUID = 1L;
    
 
}