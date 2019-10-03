package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.__kernel__.field.container.FieldContainerCollectionFunctions;

public interface FieldContainerFunctions extends FieldContainerCollectionFunctions<FunctionDto> {

	@Override
	default Class<FunctionDto> getFunctionClass() {
		return FunctionDto.class;
	}

}
