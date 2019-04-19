package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;

@Singleton
public class RoleFunctionBusinessImpl extends AbstractBusinessEntityImpl<RoleFunction, RoleFunctionPersistence> implements RoleFunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<RoleFunction> __getPersistenceEntityClass__() {
		return RoleFunction.class;
	}
	
}
