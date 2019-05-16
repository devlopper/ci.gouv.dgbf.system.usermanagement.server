package ci.gouv.dgbf.system.usermanagement.server.representation.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.__kernel__.annotation.Representation;
import org.cyk.utility.__kernel__.annotation.Server;
import org.cyk.utility.__kernel__.annotation.System;
import org.cyk.utility.instance.InstanceBuilder;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		__inject__(ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
		__setQualifiersClasses__(InstanceBuilder.class, System.class,Server.class,Representation.class);
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
