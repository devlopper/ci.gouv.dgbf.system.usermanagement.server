package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.Collection;

import org.cyk.utility.server.persistence.jpa.hierarchy.Hierarchies;

public interface PrivilegeTypeHierarchies extends Hierarchies<PrivilegeTypeHierarchy,PrivilegeType> {

	@Override PrivilegeTypeHierarchies add(Collection<PrivilegeTypeHierarchy> privilegeTypeHierarchies);

}
