package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.__kernel__.field.container.FieldContainerCollectionScopes;

public interface FieldContainerScopes extends FieldContainerCollectionScopes<ScopeDto> {
	
	@Override
	default Class<ScopeDto> getScopeClass() {
		return ScopeDto.class;
	}
	
}
