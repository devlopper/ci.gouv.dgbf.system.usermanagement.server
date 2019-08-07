package ci.gouv.dgbf.system.usermanagement.server.business.api.account.role;

import org.cyk.utility.server.business.hierarchy.HierarchyBusiness;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;

public interface PrivilegeTypeHierarchyBusiness extends HierarchyBusiness<PrivilegeTypeHierarchy,PrivilegeType,PrivilegeTypeHierarchies> {

}
