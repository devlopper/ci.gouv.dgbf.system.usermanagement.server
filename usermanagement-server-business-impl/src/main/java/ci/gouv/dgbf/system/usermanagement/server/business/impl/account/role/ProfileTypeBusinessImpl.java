package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;

@ApplicationScoped
public class ProfileTypeBusinessImpl extends AbstractBusinessEntityImpl<ProfileType, ProfileTypePersistence> implements ProfileTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ProfileType> __getPersistenceEntityClass__() {
		return ProfileType.class;
	}
	
}
