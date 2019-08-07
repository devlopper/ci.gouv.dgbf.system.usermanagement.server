package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.server.representation.hierarchy.AbstractNodeMapperImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypes;

@Mapper
public abstract class PrivilegeTypeDtoMapper extends AbstractNodeMapperImpl<PrivilegeTypeDto, PrivilegeType,PrivilegeTypeDtoCollection,PrivilegeTypes> {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected Class<PrivilegeTypeDtoCollection> __getSourceCollectionClass__() {
		return PrivilegeTypeDtoCollection.class;
	}
	
	@Override
	protected Class<PrivilegeTypes> __getDestinationCollectionClass__() {
		return PrivilegeTypes.class;
	}
	
	@Override
	protected Class<PrivilegeType> __getDestinationClass__() {
		return PrivilegeType.class;
	}
 
}