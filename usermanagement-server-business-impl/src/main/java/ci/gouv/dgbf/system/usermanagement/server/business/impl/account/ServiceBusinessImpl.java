package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.ServiceBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.ServicePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;

@ApplicationScoped
public class ServiceBusinessImpl extends AbstractBusinessEntityImpl<Service, ServicePersistence> implements ServiceBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Service> __getPersistenceEntityClass__() {
		return Service.class;
	}
	
}
