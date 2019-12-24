package ci.gouv.dgbf.system.usermanagement.server.representation.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.server.representation.AbstractRepresentationFunctionReaderImpl;
import org.cyk.utility.server.representation.DataLoader;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		AbstractRepresentationFunctionReaderImpl.QUERY_NUMBER_OF_TUPLE = 30;
		__inject__(ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
		__setQualifierClassTo__(ci.gouv.dgbf.system.usermanagement.server.annotation.System.class,DataLoader.class);
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
