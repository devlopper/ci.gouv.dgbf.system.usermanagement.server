package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.__kernel__.field.container.FieldContainerCollectionFunctionScopes;

public interface FieldContainerFunctionScopes extends FieldContainerCollectionFunctionScopes<FunctionScopeDto> {
	
	@Override
	default Class<FunctionScopeDto> getFunctionScopeClass() {
		return FunctionScopeDto.class;
	}
	
}
