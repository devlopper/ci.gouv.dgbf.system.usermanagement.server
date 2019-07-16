package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionRemover;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileServiceResourceBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileServiceResource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ServiceResource;

@ApplicationScoped
public class ProfileServiceResourceBusinessImpl extends AbstractBusinessEntityImpl<ProfileServiceResource, ProfileServiceResourcePersistence> implements ProfileServiceResourceBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(ProfileServiceResource profileServiceResource, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(profileServiceResource, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				ServiceResource serviceResource = __inject__(ServiceResourcePersistence.class).readByServiceByResource(profileServiceResource.getService(), profileServiceResource.getResource());
				if(serviceResource == null) {
					serviceResource = new ServiceResource().setService(profileServiceResource.getService()).setResource(profileServiceResource.getResource());
					__create__(serviceResource);
				}
			}
		});
	}
	
	@Override
	protected void __listenExecuteDeleteBefore__(ProfileServiceResource profileServiceResource, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteBefore__(profileServiceResource, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				ServiceResource serviceResource = __inject__(ServiceResourcePersistence.class).readByServiceByResource(profileServiceResource.getService(), profileServiceResource.getResource());
				if(serviceResource != null) {
					__delete__(serviceResource);
				}
			}
		});
	}
	
	@Override
	protected Boolean __isCallDeleteByInstanceOnDeleteByIdentifier__() {
		return Boolean.TRUE;
	}
	
	@Override
	protected Class<ProfileServiceResource> __getPersistenceEntityClass__() {
		return ProfileServiceResource.class;
	}
	
}
