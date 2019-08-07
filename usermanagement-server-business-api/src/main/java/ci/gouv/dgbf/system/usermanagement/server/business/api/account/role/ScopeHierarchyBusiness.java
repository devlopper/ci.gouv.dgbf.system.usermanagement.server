package ci.gouv.dgbf.system.usermanagement.server.business.api.account.role;

import org.cyk.utility.server.business.hierarchy.HierarchyBusiness;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchy;

public interface ScopeHierarchyBusiness extends HierarchyBusiness<ScopeHierarchy,Scope,ScopeHierarchies> {

}
