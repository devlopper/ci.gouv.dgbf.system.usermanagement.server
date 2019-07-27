package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;

@ApplicationScoped
public class PrivilegeBusinessImpl extends AbstractBusinessEntityImpl<Privilege, PrivilegePersistence> implements PrivilegeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Privilege> __getPersistenceEntityClass__() {
		return Privilege.class;
	}
	
}
