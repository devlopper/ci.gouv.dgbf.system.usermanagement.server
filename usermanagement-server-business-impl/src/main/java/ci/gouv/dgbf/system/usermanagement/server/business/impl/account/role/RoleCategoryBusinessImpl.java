package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;

@Singleton
public class RoleCategoryBusinessImpl extends AbstractBusinessEntityImpl<RoleCategory, RoleCategoryPersistence> implements RoleCategoryBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<RoleCategory> __getPersistenceEntityClass__() {
		return RoleCategory.class;
	}
	
}
