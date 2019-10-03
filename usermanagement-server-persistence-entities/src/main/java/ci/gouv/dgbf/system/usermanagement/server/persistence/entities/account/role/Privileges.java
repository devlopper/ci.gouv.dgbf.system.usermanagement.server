package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.Collection;

import org.cyk.utility.__kernel__.collection.CollectionInstance;

@Deprecated
public interface Privileges extends CollectionInstance<Privilege> {

	@Override Privileges add(Collection<Privilege> privileges);
	
}
