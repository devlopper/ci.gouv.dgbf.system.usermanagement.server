package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import org.cyk.utility.server.persistence.jpa.hierarchy.HierarchyPersistence;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;

public interface ScopeTypeHierarchyPersistence extends HierarchyPersistence<ScopeTypeHierarchy,ScopeType,ScopeTypeHierarchies> {

}
