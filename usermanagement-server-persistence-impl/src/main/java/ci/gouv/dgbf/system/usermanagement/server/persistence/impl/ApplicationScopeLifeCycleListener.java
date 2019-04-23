package ci.gouv.dgbf.system.usermanagement.server.persistence.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.ServicePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.Keycloak;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		__setQualifierClassTo__(Keycloak.Class.class, RolePersistence.class,RoleCategoryPersistence.class,RoleFunctionPersistence.class,RolePostePersistence.class
				,ServicePersistence.class,UserAccountPersistence.class);
		__inject__(KeycloakHelper.class).setClient(__inject__(KeycloakHelper.class).getClient());
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
