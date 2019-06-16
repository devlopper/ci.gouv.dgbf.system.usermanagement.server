package ci.gouv.dgbf.system.usermanagement.server.deployment;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.cyk.utility.server.deployment.AbstractServletContextListener;

import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;
import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

@WebListener
public class ServletContextListener extends AbstractServletContextListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(ServletContext context) {
		super.__initialize__(context);
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		__inject__(KeycloakHelper.class).setClient(__inject__(KeycloakHelper.class).getClient());
	}
	
}
