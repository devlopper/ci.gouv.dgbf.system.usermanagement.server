package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

@ApplicationScoped
public class AccountPersistenceImpl extends AbstractPersistenceEntityImpl<Account> implements AccountPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}
