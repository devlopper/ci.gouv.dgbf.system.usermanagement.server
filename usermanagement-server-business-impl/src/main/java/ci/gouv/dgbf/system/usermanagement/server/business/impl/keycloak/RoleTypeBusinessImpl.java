package ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.keycloak.representations.idm.RoleRepresentation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RoleTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;

@Keycloak
public class RoleTypeBusinessImpl extends AbstractBusinessEntityImpl<RoleType, RoleTypePersistence> implements RoleTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<RoleType> __getPersistenceEntityClass__() {
		return RoleType.class;
	}
	
	@Override
	public Collection<RoleType> findMany(Properties properties) {
		Collection<RoleType> roleTypes = new ArrayList<>();
		Properties keycloakProperties = new Properties();
		Map<String,List<String>> attributes = new HashMap<>();
		attributes.put("entity", (List<String>) __inject__(CollectionHelper.class).instanciate(List.class,"type"));
		keycloakProperties.setAttributes(attributes);
		Collection<RoleRepresentation> roleRepresentations = __inject__(KeycloakHelper.class).getRoles(keycloakProperties);
		for(RoleRepresentation index : roleRepresentations)
			roleTypes.add(new RoleType().setCode(index.getName()));
		return roleTypes;
	}
	
}
