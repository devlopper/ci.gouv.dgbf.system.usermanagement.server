package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;

@Singleton
public class RoleTypePersistenceImpl extends AbstractPersistenceEntityImpl<RoleType> implements RoleTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
