package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserNaturalPersonPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserNaturalPerson;

@Singleton
public class UserNaturalPersonPersistenceImpl extends AbstractPersistenceEntityImpl<UserNaturalPerson> implements UserNaturalPersonPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
