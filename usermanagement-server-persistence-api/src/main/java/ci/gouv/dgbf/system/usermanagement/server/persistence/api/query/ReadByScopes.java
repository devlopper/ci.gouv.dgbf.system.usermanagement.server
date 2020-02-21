package ci.gouv.dgbf.system.usermanagement.server.persistence.api.query;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

public interface ReadByScopes<ENTITY> {

	Collection<ENTITY> readByScopesCodes(Collection<String> codes,Properties properties);
	
	default Collection<ENTITY> readByScopesCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return readByScopesCodes(codes,null);
	}
	
	default Collection<ENTITY> readByScopesCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByScopesCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<ENTITY> readByScopesCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByScopesCodes(CollectionHelper.listOf(codes),null);
	}
	
	default Collection<ENTITY> readByScopes(Collection<Scope> actions,Properties properties) {
		if(CollectionHelper.isEmpty(actions))
			return null;
		return readByScopesCodes(actions.stream().map(Scope::getCode).collect(Collectors.toList()), properties);
	}
	
	default Collection<ENTITY> readByScopes(Collection<Scope> actions) {
		if(CollectionHelper.isEmpty(actions))
			return null;
		return readByScopes(actions,null);
	}
	
	default Collection<ENTITY> readByScopes(Properties properties,Scope...actions) {
		if(ArrayHelper.isEmpty(actions))
			return null;
		return readByScopes(CollectionHelper.listOf(actions),properties);
	}
	
	default Collection<ENTITY> readByScopes(Scope...actions) {
		if(ArrayHelper.isEmpty(actions))
			return null;
		return readByScopes(CollectionHelper.listOf(actions),null);
	}
}
