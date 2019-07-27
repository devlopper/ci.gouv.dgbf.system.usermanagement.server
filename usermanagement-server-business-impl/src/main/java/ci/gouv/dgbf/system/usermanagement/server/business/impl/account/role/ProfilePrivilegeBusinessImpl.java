package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfilePrivilegeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;

@ApplicationScoped
public class ProfilePrivilegeBusinessImpl extends AbstractBusinessEntityImpl<ProfilePrivilege, ProfilePrivilegePersistence> implements ProfilePrivilegeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ProfilePrivilege> __getPersistenceEntityClass__() {
		return ProfilePrivilege.class;
	}
	
}
