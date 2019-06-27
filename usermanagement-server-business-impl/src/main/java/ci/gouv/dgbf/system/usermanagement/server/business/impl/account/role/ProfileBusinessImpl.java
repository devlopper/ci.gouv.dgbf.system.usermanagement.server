package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

@ApplicationScoped
public class ProfileBusinessImpl extends AbstractBusinessEntityImpl<Profile, ProfilePersistence> implements ProfileBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Profile> __getPersistenceEntityClass__() {
		return Profile.class;
	}
	
}
