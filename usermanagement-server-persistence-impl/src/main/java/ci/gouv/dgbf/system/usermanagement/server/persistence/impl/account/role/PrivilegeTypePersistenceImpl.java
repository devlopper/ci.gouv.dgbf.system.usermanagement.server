package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;

@ApplicationScoped
public class PrivilegeTypePersistenceImpl extends AbstractPersistenceEntityImpl<PrivilegeType> implements PrivilegeTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<PrivilegeType> getEntityClass() {
		return PrivilegeType.class;
	}
}
