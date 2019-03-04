package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.persistence.jpa.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserLegalPersonPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserLegalPerson;

@Singleton
public class UserLegalPersonPersistenceImpl extends AbstractPersistenceEntityImpl<UserLegalPerson> implements UserLegalPersonPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
