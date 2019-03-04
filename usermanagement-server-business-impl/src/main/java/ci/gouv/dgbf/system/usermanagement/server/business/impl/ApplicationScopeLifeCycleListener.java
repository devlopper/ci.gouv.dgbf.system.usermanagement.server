package ci.gouv.dgbf.system.usermanagement.server.business.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.network.protocol.ProtocolDefaults;
import org.cyk.utility.network.protocol.ProtocolSimpleMailTransfer;
import org.cyk.utility.security.Credentials;
import org.cyk.utility.system.node.SystemNodeServer;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		/*
		__inject__(RoleBusiness.class).createMany(__inject__(CollectionHelper.class).instanciate(
				new Role().setCode("ADMINISTRATEUR").setName("Administrateur")
				,new Role().setCode("ASSISTANT").setName("Assistants")
				,new Role().setCode("CHARGE_ETUDE").setName("Chargés d’études")
				,new Role().setCode("COORDONNATEUR").setName("Coordonnateur")
				,new Role().setCode("DIRECTEUR").setName("Directeur")
				,new Role().setCode("AGENT_SAISIE").setName("Agent de saisie")
				,new Role().setCode("RUA").setName("Responsable d’unité administrative")
				,new Role().setCode("OBSERVATEUR").setName("Observateur")
				,new Role().setCode("RPROG").setName("Responsable de programme")
				,new Role().setCode("RBOP").setName("Responsable de budget opérationnel de programme")
				,new Role().setCode("RUO").setName("Responsable d’unité opérationnelle")
				,new Role().setCode("RFFIM").setName("Responsable de la fonction financière du ministère")
				,new Role().setCode("AC").setName("Administrateurs de crédits")
				,new Role().setCode("OP").setName("Ordonnateur principal")
				,new Role().setCode("OD").setName("Ordonnateur délégué")
				,new Role().setCode("CF").setName("Contrôleur financier")
				,new Role().setCode("CB").setName("Contrôleur budgétaire")
				,new Role().setCode("CA").setName("Comptable Assignataire")
				));
		*/
		__inject__(SystemNodeServer.class).setName("Gestion des utilisateurs");
		
		__inject__(ProtocolDefaults.class).get(ProtocolSimpleMailTransfer.class)
		.setHost("smtp.gmail.com").setPort(587).setIsAuthenticationRequired(Boolean.TRUE).setIsSecuredConnectionRequired(Boolean.TRUE)
		.setAuthenticationCredentials(__inject__(Credentials.class).setIdentifier("dgbfdtideveloppers").setSecret("dgbf2016dti"));
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
