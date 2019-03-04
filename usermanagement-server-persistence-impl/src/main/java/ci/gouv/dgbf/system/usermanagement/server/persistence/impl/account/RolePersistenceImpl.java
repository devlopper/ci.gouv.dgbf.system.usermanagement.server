package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.persistence.jpa.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;

@Singleton
public class RolePersistenceImpl extends AbstractPersistenceEntityImpl<Role> implements RolePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
