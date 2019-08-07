package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractHierarchiesImpl;

public class ScopeHierarchiesImpl extends AbstractHierarchiesImpl<ScopeHierarchy,Scope> implements ScopeHierarchies,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public ScopeHierarchies add(Collection<ScopeHierarchy> collection) {
		return (ScopeHierarchies) super.add(collection);
	}

}
