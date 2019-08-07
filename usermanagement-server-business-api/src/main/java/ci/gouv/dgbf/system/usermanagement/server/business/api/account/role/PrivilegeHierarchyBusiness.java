package ci.gouv.dgbf.system.usermanagement.server.business.api.account.role;

import org.cyk.utility.server.business.hierarchy.HierarchyBusiness;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;

public interface PrivilegeHierarchyBusiness extends HierarchyBusiness<PrivilegeHierarchy,Privilege,PrivilegeHierarchies> {

}
