package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.Collection;

import org.cyk.utility.server.persistence.jpa.hierarchy.Hierarchies;

public interface PrivilegeHierarchies extends Hierarchies<PrivilegeHierarchy,Privilege> {

	@Override PrivilegeHierarchies add(Collection<PrivilegeHierarchy> privilegeHierarchies);

}
