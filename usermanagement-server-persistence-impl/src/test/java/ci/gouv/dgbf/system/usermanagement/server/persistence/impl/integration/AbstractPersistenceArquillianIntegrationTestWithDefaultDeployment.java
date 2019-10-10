package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.ApplicationScopeLifeCycleListener;

public abstract class AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment extends org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
		super.__listenBeforeCallCountIsZero__();	
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);		
	}
	
}
