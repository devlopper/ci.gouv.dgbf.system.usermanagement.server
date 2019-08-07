package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.Collection;

import org.cyk.utility.server.persistence.jpa.hierarchy.Hierarchies;

public interface ScopeTypeHierarchies extends Hierarchies<ScopeTypeHierarchy,ScopeType> {

	@Override ScopeTypeHierarchies add(Collection<ScopeTypeHierarchy> scopeTypeHierarchies);

}
