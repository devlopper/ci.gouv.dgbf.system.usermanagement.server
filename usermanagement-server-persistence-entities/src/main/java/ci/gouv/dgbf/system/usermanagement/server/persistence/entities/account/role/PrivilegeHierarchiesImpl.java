package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractHierarchiesImpl;

public class PrivilegeHierarchiesImpl extends AbstractHierarchiesImpl<PrivilegeHierarchy,Privilege> implements PrivilegeHierarchies,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PrivilegeHierarchies add(Collection<PrivilegeHierarchy> collection) {
		return (PrivilegeHierarchies) super.add(collection);
	}

}
