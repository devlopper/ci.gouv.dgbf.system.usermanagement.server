package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.value.ValueUsageType;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.representations.idm.ClientRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractServicePersistenceImpl;

@Singleton @Keycloak
public class ServicePersistenceImpl extends AbstractServicePersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public PersistenceServiceProvider<Service> create(Service service, Properties properties) {
		ClientRepresentation clientRepresentation = new ClientRepresentation();
		clientRepresentation.setClientId(service.getCode());
		clientRepresentation.setName(service.getName());
		clientRepresentation.setDescription(service.getDescription());
		clientRepresentation.setBaseUrl(service.getUniformResourceLocator());
		
		Map<String,String> attributes = new LinkedHashMap<>();
		attributes.put("uuid", UUID.randomUUID().toString());
		clientRepresentation.setAttributes(attributes);
		
		ClientsResource clientsResource = __inject__(KeycloakHelper.class).getClientsResource();
		clientsResource.create(clientRepresentation);
		
		return this;
	}
	
	@Override
	public Collection<Service> read(Properties properties) {
		Collection<Service> services = new ArrayList<>();
		Collection<ClientRepresentation> clientRepresentations = __inject__(KeycloakHelper.class).getClientsResource().findAll();
		for(ClientRepresentation index : clientRepresentations)
			services.add(__instanciate__(index));
		return services;
	}
	
	@Override
	public Service readOne(Object identifier, Properties properties) {
		ValueUsageType valueUsageType = (ValueUsageType) __injectValueHelper__().defaultToIfNull(Properties.getFromPath(properties, Properties.VALUE_USAGE_TYPE),ValueUsageType.BUSINESS);	
		ClientsResource clientsResource = __inject__(KeycloakHelper.class).getClientsResource();
		String identifierAsString = (String) identifier;
		ClientRepresentation clientRepresentation = ValueUsageType.SYSTEM.equals(valueUsageType) ? clientsResource.get(identifierAsString) .toRepresentation() 
				: __injectCollectionHelper__().getFirst(clientsResource.findByClientId(identifierAsString));
		return __instanciate__(clientRepresentation);
	}
	
	public static Service __instanciate__(ClientRepresentation clientRepresentation) {
		Service service = null;
		if(clientRepresentation != null) {
			service = new Service()
					.setIdentifier(clientRepresentation.getId())
					.setCode(clientRepresentation.getClientId())
					.setName(clientRepresentation.getName())
					.setDescription(clientRepresentation.getDescription())
					.setUniformResourceLocator(clientRepresentation.getBaseUrl())
					;
		}
		return service;
	}
	
	public static Collection<Service> __instanciate__(Collection<ClientRepresentation> clientRepresentations) {
		return clientRepresentations == null ? null : clientRepresentations.stream().map(x -> __instanciate__(x)).collect(Collectors.toList());
	}
	
	@Override
	public PersistenceServiceProvider<Service> update(Service service, Properties properties) {
		ClientsResource clientsResource = __inject__(KeycloakHelper.class).getClientsResource();
		ClientResource clientResource = clientsResource.get(service.getIdentifier());
		ClientRepresentation clientRepresentation = clientResource.toRepresentation();
		clientRepresentation.setClientId(service.getCode());
		clientRepresentation.setName(service.getName());
		clientRepresentation.setDescription(service.getDescription());
		clientRepresentation.setBaseUrl(service.getUniformResourceLocator());
		clientResource.update(clientRepresentation);
		
		try{
			//updateAttributes(service, clientsResource.get(service.getIdentifier()));
		}catch(NotFoundException exception) {
			
		}
		return this;
	}

	@Override
	public PersistenceServiceProvider<Service> delete(Service service, Properties properties) {
		try{
			__inject__(KeycloakHelper.class).getClientsResource().get(service.getIdentifier()).remove();
		}catch(NotFoundException exception) {
			
		}
		return this;
	}
}
