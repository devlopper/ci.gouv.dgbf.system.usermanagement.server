package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileRoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileRoleFunction;

@ApplicationScoped
public class ProfileRoleFunctionPersistenceImpl extends AbstractPersistenceEntityImpl<ProfileRoleFunction> implements ProfileRoleFunctionPersistence, Serializable {
	private static final long serialVersionUID = 1L;

}
