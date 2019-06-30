package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;

@ApplicationScoped
public class FunctionCategoryBusinessImpl extends AbstractBusinessEntityImpl<FunctionCategory, FunctionCategoryPersistence> implements FunctionCategoryBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<FunctionCategory> __getPersistenceEntityClass__() {
		return FunctionCategory.class;
	}
	
}
