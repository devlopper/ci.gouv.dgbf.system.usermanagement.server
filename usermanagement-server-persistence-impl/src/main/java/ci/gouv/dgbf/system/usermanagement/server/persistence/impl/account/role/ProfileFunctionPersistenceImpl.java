package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

@ApplicationScoped
public class ProfileFunctionPersistenceImpl extends AbstractPersistenceEntityImpl<ProfileFunction> implements ProfileFunctionPersistence, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<ProfileFunction> getEntityClass() {
		return ProfileFunction.class;
	}
	
}
