package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.__kernel__.field.container.FieldContainerCollectionPrivileges;

public interface FieldContainerPrivileges extends FieldContainerCollectionPrivileges<PrivilegeDto> {
	
	@Override
	default Class<PrivilegeDto> getPrivilegeClass() {
		return PrivilegeDto.class;
	}
	
}
