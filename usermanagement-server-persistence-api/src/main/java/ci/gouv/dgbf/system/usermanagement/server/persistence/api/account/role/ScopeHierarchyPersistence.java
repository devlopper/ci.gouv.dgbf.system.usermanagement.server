package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import org.cyk.utility.server.persistence.jpa.hierarchy.HierarchyPersistence;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchy;

public interface ScopeHierarchyPersistence extends HierarchyPersistence<ScopeHierarchy,Scope,ScopeHierarchies> {

}
