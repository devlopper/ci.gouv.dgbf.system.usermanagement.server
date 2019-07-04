package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;

@ApplicationScoped
public class ResourcePersistenceImpl extends AbstractPersistenceEntityImpl<Resource> implements ResourcePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PersistenceServiceProvider<Resource> create(Resource resource, Properties properties) {
		super.create(resource, properties);
		//__inject__(KeycloakHelper.class).createResource(resource);
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<Resource> delete(Resource resource, Properties properties) {
		//__inject__(KeycloakHelper.class).deleteResource(resource);
		return super.delete(resource, properties);
	}
	
}
