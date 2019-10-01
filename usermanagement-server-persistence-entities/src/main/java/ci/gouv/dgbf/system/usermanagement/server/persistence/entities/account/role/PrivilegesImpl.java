package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.utility.__kernel__.collection.AbstractCollectionInstanceImpl;

public class PrivilegesImpl extends AbstractCollectionInstanceImpl<Privilege> implements Privileges,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Privileges add(Collection<Privilege> collection) {
		return (Privileges) super.add(collection);
	}
	
}
