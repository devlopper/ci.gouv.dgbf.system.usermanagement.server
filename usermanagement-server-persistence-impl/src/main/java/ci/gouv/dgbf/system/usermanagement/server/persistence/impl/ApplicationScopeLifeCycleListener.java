package ci.gouv.dgbf.system.usermanagement.server.persistence.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;

import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		__inject__(KeycloakHelper.class).setClient(__inject__(KeycloakHelper.class).getClient());
		//__inject__(KeycloakHelper.class).load();
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
