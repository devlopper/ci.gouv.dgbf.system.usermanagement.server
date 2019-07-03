package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ProfilePersistenceImpl extends AbstractPersistenceEntityImpl<Profile> implements ProfilePersistence, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PersistenceServiceProvider<Profile> create(Profile profile, Properties properties) {
		super.create(profile, properties);
		__createIntoKeycloak__(profile);
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<Profile> delete(Profile profile, Properties properties) {
		super.delete(profile, properties);
		__inject__(KeycloakHelper.class).deleteRole(profile.getCode());
		return this;
	}

	@Override
	public ProfilePersistence exportToKeycloak() {
		Collection<Profile> profiles = readMany();
		if(profiles != null)
			for(Profile index : profiles)
				__createIntoKeycloak__(index);
		return this;
	}
	
	private void __createIntoKeycloak__(Profile profile) {
		__inject__(KeycloakHelper.class).createRole(profile.getCode(), profile.getName(),"PROFILE");
	}
	
}
