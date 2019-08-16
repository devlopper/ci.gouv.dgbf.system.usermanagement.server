package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;

@ApplicationScoped
public class ProfileBusinessImpl extends AbstractBusinessEntityImpl<Profile, ProfilePersistence> implements ProfileBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(Profile profile, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(profile, properties, function);
		if(profile.getType() == null)
			profile.setType(__inject__(ProfileTypePersistence.class).readByBusinessIdentifier(ProfileType.CODE_SYSTEM)); //TODO use concept of default value
	}
	
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
	protected void __listenExecuteUpdateBefore__(Profile profile, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateBefore__(profile, properties, function);
		Strings fields = __getFieldsFromProperties__(properties);
		if(__injectCollectionHelper__().isNotEmpty(fields)) {
			for(String index : fields.get()) {
				if(Profile.FIELD_FUNCTIONS.equals(index)) {
					Collection<ProfileFunction> databaseCollection = __inject__(ProfileFunctionPersistence.class).readByProfiles(Arrays.asList(profile));
					Collection<Function> databaseFunctions = __injectCollectionHelper__().isEmpty(databaseCollection) ? null : databaseCollection.stream()
							.map(ProfileFunction::getFunction).collect(Collectors.toList());
					
					__delete__(profile.getFunctions(), databaseCollection,ProfileFunction.FIELD_FUNCTION);
					__save__(ProfileFunction.class,profile.getFunctions(), databaseFunctions, ProfileFunction.FIELD_FUNCTION, profile, ProfileFunction.FIELD_PROFILE);			
				}else if(Profile.FIELD_PRIVILEGES.equals(index)) {
					Collection<ProfilePrivilege> databaseCollection = __inject__(ProfilePrivilegePersistence.class).readByProfiles(Arrays.asList(profile));
					Collection<Privilege> databasePrivileges = __injectCollectionHelper__().isEmpty(databaseCollection) ? null : databaseCollection.stream()
							.map(ProfilePrivilege::getPrivilege).collect(Collectors.toList());
					
					__delete__(profile.getPrivileges(), databaseCollection,ProfilePrivilege.FIELD_PRIVILEGE);
					__save__(ProfilePrivilege.class,profile.getPrivileges(), databasePrivileges, ProfilePrivilege.FIELD_PRIVILEGE, profile, ProfilePrivilege.FIELD_PROFILE);	
				}
			}
		}
		
	}
	
	@Override
	protected void __listenExecuteDeleteBefore__(Profile profile, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteBefore__(profile, properties, function);
		function.addTryBeginRunnables(new Runnable() {
			@Override
			public void run() {
				Collection<Profile> profiles = Arrays.asList(profile);
				//__deleteMany__(__inject__(UserAccountProfilePersistence.class).readByProfiles(profiles)); //Delete user account first
				__deleteMany__(__inject__(ProfileFunctionPersistence.class).readByProfiles(profiles));
				__deleteMany__(__inject__(ProfilePrivilegePersistence.class).readByProfiles(profiles));
				__deleteMany__(__inject__(ProfileServiceResourcePersistence.class).readByProfiles(profiles));
			}
		});
	}
	
	@Override
	protected Boolean __isCallDeleteByInstanceOnDeleteByIdentifier__() {
		return Boolean.TRUE;
	}
		
}
