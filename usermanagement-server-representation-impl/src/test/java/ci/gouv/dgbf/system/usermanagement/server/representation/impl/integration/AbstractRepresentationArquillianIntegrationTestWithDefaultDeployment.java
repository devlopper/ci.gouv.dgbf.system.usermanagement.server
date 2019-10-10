package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration;

import org.cyk.utility.__kernel__.collection.CollectionHelper;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

public abstract class AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment extends org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		__inject__(ProfileTypeBusiness.class).createMany(CollectionHelper.listOf(new ProfileType().setCode(ProfileType.CODE_SYSTEM).setName("Système")
				,new ProfileType().setCode(ProfileType.CODE_UTILISATEUR).setName("Utilisateur")));
	}
	
}
