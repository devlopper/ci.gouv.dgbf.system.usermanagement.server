package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ServiceResource;

public interface ServiceResourcePersistence extends PersistenceEntity<ServiceResource> {

	ServiceResource readByServiceByResource(Service service,Resource resource);
	
}
