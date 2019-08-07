package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.server.representation.hierarchy.AbstractNodeMapperImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scopes;

@Mapper
public abstract class ScopeDtoMapper extends AbstractNodeMapperImpl<ScopeDto, Scope,ScopeDtoCollection,Scopes> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ScopeDtoCollection> __getSourceCollectionClass__() {
		return ScopeDtoCollection.class;
	}
	
	@Override
	protected Class<Scopes> __getDestinationCollectionClass__() {
		return Scopes.class;
	}
	
	@Override
	protected Class<Scope> __getDestinationClass__() {
		return Scope.class;
	}
	
}