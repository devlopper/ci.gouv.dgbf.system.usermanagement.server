package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.ServicePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;

@Singleton
public class ServicePersistenceImpl extends AbstractPersistenceEntityImpl<Service> implements ServicePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
