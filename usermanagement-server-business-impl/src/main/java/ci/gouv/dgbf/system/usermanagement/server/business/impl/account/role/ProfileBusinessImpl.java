package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;
import org.cyk.utility.server.business.BusinessFunctionRemover;
import org.cyk.utility.string.Strings;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

@ApplicationScoped
public class ProfileBusinessImpl extends AbstractBusinessEntityImpl<Profile, ProfilePersistence> implements ProfileBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateAfter__(Profile profile, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(profile, properties, function);
		if(Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(profile.getFunctions()))) {
			Collection<ProfileFunction> profileFunctions = new ArrayList<>();
			for(Function index : profile.getFunctions().get())
				profileFunctions.add(new ProfileFunction().setProfile(profile).setFunction(index));
			__inject__(ProfileFunctionBusiness.class).createMany(profileFunctions);
		}
	}
	
	@Override
	protected void __processAfterRead__(Profile profile,Properties properties) {
		super.__processAfterRead__(profile,properties);
		Strings fields = __getFieldsFromProperties__(properties);
		if(__injectCollectionHelper__().isNotEmpty(fields))
			fields.get().forEach(new Consumer<String>() {
				@Override
				public void accept(String field) {
					if(Profile.FIELD_FUNCTIONS.equals(field)) {
						Collection<ProfileFunction> profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfiles(Arrays.asList(profile));
						if(__injectCollectionHelper__().isNotEmpty(profileFunctions))
							profile.getFunctions(Boolean.TRUE).add(profileFunctions.stream().map(ProfileFunction::getFunction).collect(Collectors.toList()));
					}
				}
			});
	}
	
	@Override
	protected void __listenExecuteUpdateBefore__(Profile profile, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateBefore__(profile, properties, function);
		Collection<ProfileFunction> databaseCollection = __inject__(ProfileFunctionPersistence.class).readByProfiles(Arrays.asList(profile));
		Collection<Function> databaseFunctions = __injectCollectionHelper__().isEmpty(databaseCollection) ? null : databaseCollection.stream()
				.map(ProfileFunction::getFunction).collect(Collectors.toList());
		
		__delete__(profile.getFunctions(), databaseCollection,ProfileFunction.FIELD_FUNCTION);
		__save__(ProfileFunction.class,profile.getFunctions(), databaseFunctions, ProfileFunction.FIELD_FUNCTION, profile, ProfileFunction.FIELD_PROFILE);
	}
	
	@Override
	protected void __listenExecuteDeleteBefore__(Profile profile, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteBefore__(profile, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				Collection<Profile> profiles = Arrays.asList(profile);
				__deleteMany__(__inject__(ProfileFunctionPersistence.class).readByProfiles(profiles));
				__deleteMany__(__inject__(ProfileServiceResourcePersistence.class).readByProfiles(profiles));
			}
		});
	}
	
	@Override
	protected Boolean __isCallDeleteByInstanceOnDeleteByIdentifier__() {
		return Boolean.TRUE;
	}
	
	@Override
	protected Class<Profile> __getPersistenceEntityClass__() {
		return Profile.class;
	}
	
}
