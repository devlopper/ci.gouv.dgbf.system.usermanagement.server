package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.Strings;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;
import org.cyk.utility.server.business.hierarchy.AbstractBusinessIdentifiedByStringAndCodedImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeProfile;

@ApplicationScoped
public class ScopeTypeBusinessImpl extends AbstractBusinessIdentifiedByStringAndCodedImpl<ScopeType, ScopeTypePersistence,ScopeTypeHierarchy,ScopeTypeHierarchies,ScopeTypeHierarchyPersistence,ScopeTypeHierarchyBusiness> implements ScopeTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateAfter__(ScopeType scopeType, Properties properties, BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(scopeType, properties, function);
		if(CollectionHelper.isNotEmpty(scopeType.getProfiles())) {
			Collection<ScopeTypeProfile> scopeTypeProfiles = scopeType.getProfiles().stream().map(x -> new ScopeTypeProfile(scopeType,x,Boolean.TRUE)).collect(Collectors.toList());
			__inject__(ScopeTypeProfileBusiness.class).createMany(scopeTypeProfiles);
		}
	}
	
	@Override
	protected void __listenExecuteUpdateBefore__(ScopeType scopeType, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateBefore__(scopeType, properties, function);
		Strings fields = __getFieldsFromProperties__(properties);
		if(CollectionHelper.isEmpty(fields))
			return;		
		for(String index : fields.get()) {
			if(ScopeType.FIELD_PROFILES.equals(index)) {
				Collection<ScopeTypeProfile> databaseScopeTypeProfiles = __inject__(ScopeTypeProfilePersistence.class).readByScopeTypes(scopeType);
				Collection<Profile> databaseProfiles = CollectionHelper.isEmpty(databaseScopeTypeProfiles) ? null : databaseScopeTypeProfiles.stream()
						.map(ScopeTypeProfile::getProfile).collect(Collectors.toList());
				
				__delete__(scopeType.getProfiles(), databaseScopeTypeProfiles,ScopeTypeProfile.FIELD_PROFILE);
				__save__(ScopeTypeProfile.class,scopeType.getProfiles(), databaseProfiles, ScopeTypeProfile.FIELD_PROFILE, scopeType, ScopeTypeProfile.FIELD_SCOPE_TYPE);
			}
		}
	}
	
	@Override
	protected <M, D> D __getSavableInstance__(Class<D> klass, M finalInstance, Collection<M> persistedInstances,String fieldName, Object master, String masterFieldName) {
		D instance = super.__getSavableInstance__(klass, finalInstance, persistedInstances, fieldName, master, masterFieldName);
		if(instance instanceof ScopeTypeProfile) {
			if(((ScopeTypeProfile)instance).getProfileOfTypeUserCreatableOnScopeCreate() == null)
				((ScopeTypeProfile)instance).setProfileOfTypeUserCreatableOnScopeCreate(Boolean.TRUE);
		}
		return instance;
	}
	
}
