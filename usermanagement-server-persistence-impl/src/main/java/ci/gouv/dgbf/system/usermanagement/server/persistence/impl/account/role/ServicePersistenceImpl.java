package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.PersistenceFunctionRemover;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ServicePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ServicePersistenceImpl extends AbstractPersistenceEntityImpl<Service> implements ServicePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateAfter__(Service service, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateAfter__(service, properties, function);
		__inject__(KeycloakHelper.class).createClient(service);
	}
	
	@Override
	protected void __listenExecuteDeleteAfter__(Service service, Properties properties,PersistenceFunctionRemover function) {
		super.__listenExecuteDeleteAfter__(service, properties, function);
		__inject__(KeycloakHelper.class).deleteClient(service);
	}
	
}
