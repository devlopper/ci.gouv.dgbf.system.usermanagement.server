package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ServicePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ServicePersistenceImpl extends AbstractPersistenceEntityImpl<Service> implements ServicePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PersistenceServiceProvider<Service> create(Service service, Properties properties) {
		super.create(service, properties);
		__inject__(KeycloakHelper.class).createClient(service);
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<Service> delete(Service service, Properties properties) {
		__inject__(KeycloakHelper.class).deleteClient(service);
		return super.delete(service, properties);
	}
	
}
