package ci.gouv.dgbf.system.usermanagement.server.persistence.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.__kernel__.DependencyInjection;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.Keycloak;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		DependencyInjection.setQualifierClass(RolePersistence.class, Keycloak.Class.class);
		DependencyInjection.setQualifierClass(RoleCategoryPersistence.class, Keycloak.Class.class);
		DependencyInjection.setQualifierClass(RoleFunctionPersistence.class, Keycloak.Class.class);
		DependencyInjection.setQualifierClass(RolePostePersistence.class, Keycloak.Class.class);
		__inject__(KeycloakHelper.class).setClient(__inject__(KeycloakHelper.class).getClient());
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
