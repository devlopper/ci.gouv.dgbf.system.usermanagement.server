package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;

@ApplicationScoped
public class PrivilegeTypeBusinessImpl extends AbstractBusinessEntityImpl<PrivilegeType, PrivilegeTypePersistence> implements PrivilegeTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<PrivilegeType> __getPersistenceEntityClass__() {
		return PrivilegeType.class;
	}
	
}
