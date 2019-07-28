package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.Collection;

import org.cyk.utility.server.persistence.hierarchy.HierarchyCollectionInstance;

public interface PrivilegeTypeHierarchies extends HierarchyCollectionInstance<PrivilegeType,PrivilegeTypeHierarchy> {

	@Override PrivilegeTypeHierarchies add(Collection<PrivilegeTypeHierarchy> privilegeTypeHierarchies);

}
