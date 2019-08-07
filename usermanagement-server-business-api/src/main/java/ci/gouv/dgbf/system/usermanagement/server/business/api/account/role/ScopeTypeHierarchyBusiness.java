package ci.gouv.dgbf.system.usermanagement.server.business.api.account.role;

import org.cyk.utility.server.business.hierarchy.HierarchyBusiness;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;

public interface ScopeTypeHierarchyBusiness extends HierarchyBusiness<ScopeTypeHierarchy,ScopeType,ScopeTypeHierarchies> {

}
