package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.utility.collection.AbstractCollectionInstanceImpl;

public class FunctionsImpl extends AbstractCollectionInstanceImpl<Function> implements Functions,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Functions add(Collection<Function> functions) {
		return (Functions) super.add(functions);
	}
	
	@Override
	public Functions add(Function...functions) {
		return (Functions) super.add(functions);
	}

}
