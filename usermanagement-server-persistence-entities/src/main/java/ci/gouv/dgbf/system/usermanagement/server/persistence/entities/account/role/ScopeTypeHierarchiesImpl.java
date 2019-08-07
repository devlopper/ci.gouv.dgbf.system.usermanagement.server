package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractHierarchiesImpl;

public class ScopeTypeHierarchiesImpl extends AbstractHierarchiesImpl<ScopeTypeHierarchy,ScopeType> implements ScopeTypeHierarchies,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public ScopeTypeHierarchies add(Collection<ScopeTypeHierarchy> collection) {
		return (ScopeTypeHierarchies) super.add(collection);
	}

}
