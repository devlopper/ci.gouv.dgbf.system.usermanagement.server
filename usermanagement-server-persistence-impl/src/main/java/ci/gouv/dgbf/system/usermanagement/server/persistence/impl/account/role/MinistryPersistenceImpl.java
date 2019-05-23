package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.MinistryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Ministry;

@Singleton
public class MinistryPersistenceImpl extends AbstractPersistenceEntityImpl<Ministry> implements MinistryPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
