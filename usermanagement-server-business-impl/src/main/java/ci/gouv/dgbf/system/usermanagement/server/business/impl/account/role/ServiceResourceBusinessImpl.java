package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ServiceResourceBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ServiceResource;

@ApplicationScoped
public class ServiceResourceBusinessImpl extends AbstractBusinessEntityImpl<ServiceResource, ServiceResourcePersistence> implements ServiceResourceBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ServiceResource> __getPersistenceEntityClass__() {
		return ServiceResource.class;
	}
	
}
