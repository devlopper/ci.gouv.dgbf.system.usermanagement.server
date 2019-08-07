package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.server.representation.hierarchy.AbstractNodeMapperImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypes;

@Mapper
public abstract class ScopeTypeDtoMapper extends AbstractNodeMapperImpl<ScopeTypeDto, ScopeType,ScopeTypeDtoCollection,ScopeTypes> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ScopeTypeDtoCollection> __getSourceCollectionClass__() {
		return ScopeTypeDtoCollection.class;
	}
	
	@Override
	protected Class<ScopeTypes> __getDestinationCollectionClass__() {
		return ScopeTypes.class;
	}
	
	@Override
	protected Class<ScopeType> __getDestinationClass__() {
		return ScopeType.class;
	}
	
}