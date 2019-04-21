package ci.gouv.dgbf.system.usermanagement.server.business.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.network.protocol.ProtocolDefaults;
import org.cyk.utility.network.protocol.ProtocolSimpleMailTransfer;
import org.cyk.utility.security.Credentials;
import org.cyk.utility.system.node.SystemNodeServer;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.Keycloak;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		DependencyInjection.setQualifierClass(RoleBusiness.class, Keycloak.Class.class);
		__inject__(SystemNodeServer.class).setName("Gestion des utilisateurs");
		
		__inject__(ProtocolDefaults.class).get(ProtocolSimpleMailTransfer.class)
		.setHost("smtp.gmail.com").setPort(587).setIsAuthenticationRequired(Boolean.TRUE).setIsSecuredConnectionRequired(Boolean.TRUE)
		.setAuthenticationCredentials(__inject__(Credentials.class).setIdentifier("dgbfdtideveloppers").setSecret("dgbf2016dti"));
		
		__inject__(ci.gouv.dgbf.system.usermanagement.server.persistence.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
