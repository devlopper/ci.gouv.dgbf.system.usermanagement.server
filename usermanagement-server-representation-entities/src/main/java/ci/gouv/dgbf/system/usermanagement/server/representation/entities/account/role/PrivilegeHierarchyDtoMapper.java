package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.server.representation.hierarchy.AbstractNodeMapperImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;

@Mapper
public abstract class PrivilegeHierarchyDtoMapper extends AbstractNodeMapperImpl<PrivilegeHierarchyDto, PrivilegeHierarchy,PrivilegeHierarchyDtoCollection,PrivilegeHierarchies> {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected Class<PrivilegeHierarchyDtoCollection> __getSourceCollectionClass__() {
		return PrivilegeHierarchyDtoCollection.class;
	}
	
	@Override
	protected Class<PrivilegeHierarchies> __getDestinationCollectionClass__() {
		return PrivilegeHierarchies.class;
	}
	
	@Override
	protected Class<PrivilegeHierarchy> __getDestinationClass__() {
		return PrivilegeHierarchy.class;
	}
 
}