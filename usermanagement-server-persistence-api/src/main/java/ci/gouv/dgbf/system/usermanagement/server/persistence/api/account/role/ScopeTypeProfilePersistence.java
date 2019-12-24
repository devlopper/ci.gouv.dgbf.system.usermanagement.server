package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeProfile;

public interface ScopeTypeProfilePersistence extends PersistenceEntity<ScopeTypeProfile> {

	Collection<ScopeTypeProfile> readByScopeTypesCodes(Collection<String> scopeTypesCodes,Properties properties);
	
	default Collection<ScopeTypeProfile> readByScopeTypesCodes(Collection<String> scopeTypesCodes) {
		if(CollectionHelper.isEmpty(scopeTypesCodes))
			return null;
		return readByScopeTypesCodes(scopeTypesCodes, null);
	}
	
	default Collection<ScopeTypeProfile> readByScopeTypesCodes(Properties properties,String...scopeTypesCodes) {
		if(ArrayHelper.isEmpty(scopeTypesCodes))
			return null;
		return readByScopeTypesCodes(CollectionHelper.listOf(scopeTypesCodes), properties);
	}
	
	default Collection<ScopeTypeProfile> readByScopeTypesCodes(String...scopeTypesCodes) {
		if(ArrayHelper.isEmpty(scopeTypesCodes))
			return null;
		return readByScopeTypesCodes(CollectionHelper.listOf(scopeTypesCodes), null);
	}
	
	default Collection<ScopeTypeProfile> readByScopeTypes(Collection<ScopeType> scopeTypes,Properties properties) {
		if(CollectionHelper.isEmpty(scopeTypes))
			return null;
		return readByScopeTypesCodes(scopeTypes.stream().map(ScopeType::getCode).collect(Collectors.toList()), properties);
	}
	
	default Collection<ScopeTypeProfile> readByScopeTypes(Collection<ScopeType> scopeTypes) {
		if(CollectionHelper.isEmpty(scopeTypes))
			return null;
		return readByScopeTypes(scopeTypes, null);
	}
	
	default Collection<ScopeTypeProfile> readByScopeTypes(Properties properties,ScopeType...scopeTypes) {
		if(ArrayHelper.isEmpty(scopeTypes))
			return null;
		return readByScopeTypes(CollectionHelper.listOf(scopeTypes), properties);
	}
	
	default Collection<ScopeTypeProfile> readByScopeTypes(ScopeType...scopeTypes) {
		if(ArrayHelper.isEmpty(scopeTypes))
			return null;
		return readByScopeTypes(CollectionHelper.listOf(scopeTypes), null);
	}
}
