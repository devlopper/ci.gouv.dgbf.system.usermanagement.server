package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;

@ApplicationScoped
public class PrivilegePersistenceImpl extends AbstractPersistenceEntityImpl<Privilege> implements PrivilegePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<Privilege> getEntityClass() {
		return Privilege.class;
	}
}
