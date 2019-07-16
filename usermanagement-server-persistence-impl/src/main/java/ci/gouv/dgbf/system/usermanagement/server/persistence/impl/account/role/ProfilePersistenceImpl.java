package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.PersistenceFunctionRemover;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ProfilePersistenceImpl extends AbstractPersistenceEntityImpl<Profile> implements ProfilePersistence, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateAfter__(Profile profile, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateAfter__(profile, properties, function);
		__createIntoKeycloak__(profile);
	}
	
	@Override
	protected void __listenExecuteDeleteAfter__(Profile profile, Properties properties,PersistenceFunctionRemover function) {
		super.__listenExecuteDeleteAfter__(profile, properties, function);
		__inject__(KeycloakHelper.class).deleteRole(profile.getCode());
	}
	
	@Override
	public ProfilePersistence exportToKeycloak() {
		Collection<Profile> profiles = read();
		if(profiles != null)
			for(Profile index : profiles)
				__createIntoKeycloak__(index);
		return this;
	}
	
	private void __createIntoKeycloak__(Profile profile) {
		__inject__(KeycloakHelper.class).createRole(profile.getCode(), profile.getName(),"PROFILE");
	}
	
}
