package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.ArrayList;
import java.util.Collection;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.field.FieldHelper;
import org.cyk.utility.__kernel__.field.container.FieldContainerCollection;
import org.cyk.utility.instance.InstanceHelper;

public interface FieldContainerScopes extends FieldContainerCollection {

	default FieldContainerScopes setScopes(Collection<Scope> scopes) {
		FieldHelper.write(this, FIELD_SCOPES, scopes);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	default Collection<Scope> getScopes() {
		return (Collection<Scope>) FieldHelper.read(this, FIELD_SCOPES);
	}
	
	default Collection<Scope> getScopes(Boolean injectIfNull) {
		Collection<Scope> scopes = getScopes();
		if(scopes == null && Boolean.TRUE.equals(injectIfNull))
			setScopes(scopes = new ArrayList<>());
		return scopes;
	}
	
	default FieldContainerScopes addScopes(Collection<Scope> scopes) {
		if(CollectionHelper.isEmpty(scopes))
			return this;
		getScopes(Boolean.TRUE).addAll(scopes);
		return this;
	}
	
	default FieldContainerScopes addScopes(Scope...scopes) {
		if(ArrayHelper.isEmpty(scopes))
			return this;
		addScopes(CollectionHelper.listOf(scopes));
		return this;
	}
	
	default FieldContainerScopes addScopesByCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return this;
		for(String index : codes)
			addScopes(DependencyInjection.inject(InstanceHelper.class).getByIdentifierBusiness(Scope.class, index));
		return this;
	}
	
	default FieldContainerScopes addScopesByCodes(String... codes) {
		if(ArrayHelper.isEmpty(codes))
			return this;
		addScopesByCodes(CollectionHelper.listOf(codes));
		return this;
	}
	
	String FIELD_SCOPES = "scopes";
	
}
