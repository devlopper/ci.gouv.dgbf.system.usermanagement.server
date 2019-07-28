package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.utility.server.persistence.hierarchy.AbstractHierarchyCollectionInstanceImpl;

public class ScopeTypeHierarchiesImpl extends AbstractHierarchyCollectionInstanceImpl<ScopeType,ScopeTypeHierarchy> implements ScopeTypeHierarchies,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public ScopeTypeHierarchies add(Collection<ScopeTypeHierarchy> collection) {
		return (ScopeTypeHierarchies) super.add(collection);
	}

}
