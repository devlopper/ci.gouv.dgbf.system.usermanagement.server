package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import org.cyk.utility.__kernel__.field.container.FieldContainerCollectionPrivileges;

public interface FieldContainerPrivileges extends FieldContainerCollectionPrivileges<Privilege> {
	
	@Override
	default Class<Privilege> getPrivilegeClass() {
		return Privilege.class;
	}
	
}
