package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import org.cyk.utility.server.persistence.jpa.hierarchy.HierarchyPersistence;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;

public interface PrivilegeHierarchyPersistence extends HierarchyPersistence<PrivilegeHierarchy,Privilege,PrivilegeHierarchies> {

}
