package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.utility.server.persistence.hierarchy.AbstractHierarchyCollectionInstanceImpl;

public class PrivilegeTypeHierarchiesImpl extends AbstractHierarchyCollectionInstanceImpl<PrivilegeType,PrivilegeTypeHierarchy> implements PrivilegeTypeHierarchies,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PrivilegeTypeHierarchies add(Collection<PrivilegeTypeHierarchy> collection) {
		return (PrivilegeTypeHierarchies) super.add(collection);
	}

}
