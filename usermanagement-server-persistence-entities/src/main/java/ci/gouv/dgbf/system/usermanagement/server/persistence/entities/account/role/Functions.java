package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.Collection;

import org.cyk.utility.collection.CollectionInstance;

public interface Functions extends CollectionInstance<Function> {

	@Override Functions add(Collection<Function> functions);
	@Override Functions add(Function...functions);
}
