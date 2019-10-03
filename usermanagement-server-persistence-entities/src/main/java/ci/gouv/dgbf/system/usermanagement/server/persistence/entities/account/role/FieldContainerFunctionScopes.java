package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import org.cyk.utility.__kernel__.field.container.FieldContainerCollectionFunctionScopes;

public interface FieldContainerFunctionScopes extends FieldContainerCollectionFunctionScopes<FunctionScope> {

	@Override
	default Class<FunctionScope> getFunctionScopeClass() {
		return FunctionScope.class;
	}
	
}
