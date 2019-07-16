package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.PersistenceFunctionRemover;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ServiceResource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ServiceResourcePersistenceImpl extends AbstractPersistenceEntityImpl<ServiceResource> implements ServiceResourcePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByServiceByResource;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByServiceByResource, "SELECT tuple FROM ServiceResource tuple WHERE tuple.service = :service AND tuple.resource = :resource");
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(ServiceResource serviceResource, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateAfter__(serviceResource, properties, function);
		__inject__(KeycloakHelper.class).createResource(serviceResource.getService(),serviceResource.getResource());
	}
	
	@Override
	public ServiceResource readByServiceByResource(Service service, Resource resource) {
		try {
			return __inject__(EntityManager.class).createNamedQuery(readByServiceByResource, ServiceResource.class).setParameter("service", service)
					.setParameter("resource", resource).getSingleResult();
		} catch (NoResultException exception) {}
		return null;
		//__readOne__(____getQueryParameters____(new Properties().setQueryIdentifier(readByServiceByResource),service,resource));
	}
	
	@Override
	protected void __listenExecuteDeleteAfter__(ServiceResource serviceResource, Properties properties,PersistenceFunctionRemover function) {
		super.__listenExecuteDeleteAfter__(serviceResource, properties, function);
		__inject__(KeycloakHelper.class).deleteResource(serviceResource.getService(),serviceResource.getResource());
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties, Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByServiceByResource))
			return new Object[]{"service", objects[0],"resource",objects[1]};
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
}
