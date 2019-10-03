package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import org.cyk.utility.__kernel__.field.container.FieldContainerCollectionFunctions;

public interface FieldContainerFunctions extends FieldContainerCollectionFunctions<Function> {

	@Override
	default Class<Function> getFunctionClass() {
		return Function.class;
	}
	
}
