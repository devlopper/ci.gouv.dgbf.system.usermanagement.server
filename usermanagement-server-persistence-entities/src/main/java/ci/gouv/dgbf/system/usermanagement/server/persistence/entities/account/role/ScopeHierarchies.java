package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.Collection;

import org.cyk.utility.server.persistence.jpa.hierarchy.Hierarchies;

public interface ScopeHierarchies extends Hierarchies<ScopeHierarchy,Scope> {

	@Override ScopeHierarchies add(Collection<ScopeHierarchy> scopeHierarchies);

}
