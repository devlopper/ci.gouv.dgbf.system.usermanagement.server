package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.util.ArrayList;
import java.util.Collection;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.field.FieldHelper;
import org.cyk.utility.__kernel__.field.container.FieldContainerCollection;
import org.cyk.utility.instance.InstanceHelper;

public interface FieldContainerProfiles extends FieldContainerCollection {

	default FieldContainerProfiles setProfiles(Collection<Profile> profiles) {
		FieldHelper.write(this, FIELD_PROFILES, profiles);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	default Collection<Profile> getProfiles() {
		return (Collection<Profile>) FieldHelper.read(this, FIELD_PROFILES);
	}
	
	default Collection<Profile> getProfiles(Boolean injectIfNull) {
		Collection<Profile> profiles = getProfiles();
		if(profiles == null && Boolean.TRUE.equals(injectIfNull))
			setProfiles(profiles = new ArrayList<>());
		return profiles;
	}
	
	default FieldContainerProfiles addProfiles(Collection<Profile> profiles) {
		if(CollectionHelper.isEmpty(profiles))
			return this;
		getProfiles(Boolean.TRUE).addAll(profiles);
		return this;
	}
	
	default FieldContainerProfiles addProfiles(Profile...profiles) {
		if(ArrayHelper.isEmpty(profiles))
			return this;
		addProfiles(CollectionHelper.listOf(profiles));
		return this;
	}
	
	default FieldContainerProfiles addProfilesByCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return this;
		for(String index : codes)
			addProfiles(DependencyInjection.inject(InstanceHelper.class).getByIdentifierBusiness(Profile.class, index));
		return this;
	}
	
	default FieldContainerProfiles addProfilesByCodes(String... codes) {
		if(ArrayHelper.isEmpty(codes))
			return this;
		addProfilesByCodes(CollectionHelper.listOf(codes));
		return this;
	}
	
	String FIELD_PROFILES = "profiles";
	
}
