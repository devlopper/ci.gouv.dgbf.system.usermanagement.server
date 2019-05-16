package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleCategoryPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Role;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.AbstractRoleFunctionPersistenceImpl;

@Singleton @Keycloak
public class RoleFunctionPersistenceImpl extends AbstractRoleFunctionPersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Collection<RoleFunction> read(Properties properties) {
		return __instanciate__(RolePersistenceImpl.__instanciate__(__inject__(KeycloakHelper.class).getRolesByProperty("type","FONCTION"))
				,__inject__(RoleCategoryPersistence.class).read());
	}
	
	@Override
	public RoleFunction readOne(Object identifier, Properties properties) {
		return __instanciate__(__inject__(RolePersistence.class).readOne(identifier, properties),__inject__(RoleCategoryPersistence.class).read());
	}
	
	public static RoleFunction __instanciate__(Role role,Collection<RoleCategory> categories) {
		RoleFunction function = new RoleFunction().setIdentifier(role.getIdentifier()).setRole(role);
		if(__inject__(CollectionHelper.class).isNotEmpty(categories)) {
			Collection<String> categoriesIdentifiers = categories.stream().map(RoleCategory::getIdentifier).collect(Collectors.toList());
			Collection<RoleRepresentation> composites = __inject__(KeycloakHelper.class).getRolesResource().get(role.getIdentifier()).getRoleComposites();
			if(composites!=null) {
				for(RoleRepresentation index : composites) {
					if(categoriesIdentifiers.contains(index.getName())) {
						for(RoleCategory indexCategory : categories)
							if(indexCategory.getIdentifier().equals(index.getName())) {
								function.setCategory(indexCategory);
								break;
							}
						break;
					}
				}
			}
		}
		
		return function;
	}
	
	public static Collection<RoleFunction> __instanciate__(Collection<Role> roles,Collection<RoleCategory> categories) {
		return roles == null ? null : roles.stream().map(x -> __instanciate__(x,categories)).collect(Collectors.toList());
	}
	
	@Override
	public Long count(Properties arg0) {
		return null;
	}
	
}
