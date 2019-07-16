package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;

@ApplicationScoped
public class FunctionCategoryPersistenceImpl extends AbstractPersistenceEntityImpl<FunctionCategory> implements FunctionCategoryPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<FunctionCategory> getEntityClass() {
		return FunctionCategory.class;
	}
}
