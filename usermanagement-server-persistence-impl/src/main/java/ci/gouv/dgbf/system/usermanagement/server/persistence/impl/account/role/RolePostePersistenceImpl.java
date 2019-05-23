package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@Singleton
public class RolePostePersistenceImpl extends AbstractRolePostePersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PersistenceServiceProvider<RolePoste> create(RolePoste rolePoste, Properties properties) {
		super.create(rolePoste, properties);
		//__inject__(KeycloakHelper.class).createRole(rolePoste.getCode(), rolePoste.getName(), "POSTE",rolePoste.getFunction().getCode());
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<RolePoste> delete(RolePoste rolePoste, Properties properties) {
		super.delete(rolePoste, properties);
		__inject__(KeycloakHelper.class).deleteRole(rolePoste.getCode());
		return this;
	}
}
