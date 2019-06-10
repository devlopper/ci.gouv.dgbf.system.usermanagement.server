package ci.gouv.dgbf.system.usermanagement.server.representation.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.field.FieldValueCopy;
import org.cyk.utility.instance.InstanceBuilder;
import org.cyk.utility.server.representation.impl.DataLoader;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		__inject__(ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
		__setQualifierClassTo__(ci.gouv.dgbf.system.usermanagement.server.annotation.System.class, InstanceBuilder.class,FieldValueCopy.class,DataLoader.class);
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
