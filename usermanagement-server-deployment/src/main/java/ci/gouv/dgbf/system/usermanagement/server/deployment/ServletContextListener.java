package ci.gouv.dgbf.system.usermanagement.server.deployment;

import java.io.Serializable;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.cyk.utility.server.deployment.AbstractServletContextListener;

import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

@WebListener
public class ServletContextListener extends AbstractServletContextListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenContextInitialized__(ServletContextEvent servletContextEvent) {
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Override
	protected void __listenContextDestroyed__(ServletContextEvent servletContextEvent) {
		
	}

}
