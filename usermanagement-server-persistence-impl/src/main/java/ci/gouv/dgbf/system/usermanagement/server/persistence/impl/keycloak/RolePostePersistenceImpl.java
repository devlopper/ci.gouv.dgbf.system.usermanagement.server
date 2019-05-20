package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractRolePostePersistenceImpl;

@Singleton @Keycloak
public class RolePostePersistenceImpl extends AbstractRolePostePersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Collection<RolePoste> read(Properties properties) {
		return __instanciate__(RolePersistenceImpl.__instanciate__(__inject__(KeycloakHelper.class).getRolesByProperty("type","POSTE"))
				,__inject__(RoleFunctionPersistence.class).read());
	}
	
	@Override
	public RolePoste readOne(Object identifier, Properties properties) {
		return __instanciate__(__inject__(RolePersistence.class).readOne(identifier, properties),__inject__(RoleFunctionPersistence.class).read());
	}
	
	public static RolePoste __instanciate__(Role role,Collection<RoleFunction> functions) {
		RolePoste rolePoste = new RolePoste().setIdentifier(role.getIdentifier());//.setRole(role);
		if(__inject__(CollectionHelper.class).isNotEmpty(functions)) {
			Collection<String> functionsIdentifiers = functions.stream().map(RoleFunction::getIdentifier).collect(Collectors.toList());
			Collection<RoleRepresentation> composites = __inject__(KeycloakHelper.class).getRolesResource().get(role.getIdentifier()).getRoleComposites();
			if(composites!=null) {
				for(RoleRepresentation index : composites) {
					if(functionsIdentifiers.contains(index.getName())) {
						for(RoleFunction indexFunction : functions)
							if(indexFunction.getIdentifier().equals(index.getName())) {
								rolePoste.setFunction(indexFunction);
								break;
							}
						break;
					}
				}
			}
		}
		return rolePoste;
	}
	
	public static Collection<RolePoste> __instanciate__(Collection<Role> roles,Collection<RoleFunction> functions) {
		return roles == null ? null : roles.stream().map(x -> __instanciate__(x,functions)).collect(Collectors.toList());
	}
	
	@Override
	public Long count(Properties arg0) {
		return null;
	}
	
}
