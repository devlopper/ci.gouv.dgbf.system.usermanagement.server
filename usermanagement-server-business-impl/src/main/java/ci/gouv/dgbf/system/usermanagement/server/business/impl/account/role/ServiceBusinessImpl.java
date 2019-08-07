package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ServiceBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ServicePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;

@ApplicationScoped
public class ServiceBusinessImpl extends AbstractBusinessEntityImpl<Service, ServicePersistence> implements ServiceBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	
}
