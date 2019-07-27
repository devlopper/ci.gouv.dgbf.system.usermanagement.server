package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;

@ApplicationScoped
public class ProfileTypePersistenceImpl extends AbstractPersistenceEntityImpl<ProfileType> implements ProfileTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<ProfileType> getEntityClass() {
		return ProfileType.class;
	}
}
