package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import org.cyk.utility.server.persistence.hierarchy.HierarchyPersistence;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;

public interface PrivilegeTypeHierarchyPersistence extends HierarchyPersistence<PrivilegeTypeHierarchy,PrivilegeType,PrivilegeTypeHierarchies> {

}
