package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractPersistenceIdentifiedByStringAndCodedImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeProfile;

@ApplicationScoped
public class ScopeTypePersistenceImpl extends AbstractPersistenceIdentifiedByStringAndCodedImpl<ScopeType,ScopeTypeHierarchy,ScopeTypeHierarchies,ScopeTypeHierarchyPersistence> implements ScopeTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(ScopeType scopeType, Field field, Properties properties) {
		super.__listenExecuteReadAfterSetFieldValue__(scopeType, field, properties);
		if(field.getName().equals(ScopeType.FIELD_PROFILES)) {
			Collection<ScopeTypeProfile> scopeTypeProfiles = __inject__(ScopeTypeProfilePersistence.class).readByScopeTypes(scopeType);
			if(CollectionHelper.isNotEmpty(scopeTypeProfiles))
				scopeType.setProfiles(scopeTypeProfiles.stream().map(ScopeTypeProfile::getProfile).collect(Collectors.toList()));
		}
	}
	
}
