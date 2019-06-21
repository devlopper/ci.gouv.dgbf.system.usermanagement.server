package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountInterimModelPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterimModel;

@ApplicationScoped
public class UserAccountInterimModelPersistenceImpl extends AbstractPersistenceEntityImpl<UserAccountInterimModel> implements UserAccountInterimModelPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
