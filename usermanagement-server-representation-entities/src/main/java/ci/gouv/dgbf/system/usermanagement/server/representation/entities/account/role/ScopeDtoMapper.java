package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.mapping.MapperSourceDestination;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

@Mapper
public interface ScopeDtoMapper extends MapperSourceDestination<ScopeDto, Scope> {
    
 
}