package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountInterimPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;

@ApplicationScoped
public class UserAccountInterimPersistenceImpl extends AbstractPersistenceEntityImpl<UserAccountInterim> implements UserAccountInterimPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
