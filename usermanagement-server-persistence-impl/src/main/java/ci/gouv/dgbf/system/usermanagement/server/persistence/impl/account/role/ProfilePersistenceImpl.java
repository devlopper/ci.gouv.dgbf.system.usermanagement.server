package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionCreator;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.PersistenceFunctionRemover;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privileges;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ProfilePersistenceImpl extends AbstractPersistenceEntityImpl<Profile> implements ProfilePersistence, Serializable {
	private static final long serialVersionUID = 1L;

	private String readByTypesCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByTypesCodes, "SELECT tuple FROM Profile tuple WHERE tuple.type.code IN :codes");
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(Profile profile, Properties properties,PersistenceFunctionCreator function) {
		super.__listenExecuteCreateAfter__(profile, properties, function);
		__createIntoKeycloak__(profile);
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(Profile profile, Field field, Properties properties) {
		if(Profile.FIELD_FUNCTIONS.equals(field.getName())) {
			Collection<ProfileFunction> profileFunctions = __inject__(ProfileFunctionPersistence.class).readByProfiles(Arrays.asList(profile));
			if(CollectionHelper.isNotEmpty(profileFunctions))
				profile.getFunctions(Boolean.TRUE).add(profileFunctions.stream().map(ProfileFunction::getFunction).collect(Collectors.toList()));
		}else if(Profile.FIELD_PRIVILEGES.equals(field.getName())) {
			Collection<ProfilePrivilege> profilePrivileges = __inject__(ProfilePrivilegePersistence.class).readByProfiles(Arrays.asList(profile));
			if(CollectionHelper.isNotEmpty(profilePrivileges))
				profile.getPrivileges(Boolean.TRUE).add(profilePrivileges.stream().map(ProfilePrivilege::getPrivilege).collect(Collectors.toList()));
		}
	}
	
	@Override
	protected void __listenExecuteDeleteAfter__(Profile profile, Properties properties,PersistenceFunctionRemover function) {
		super.__listenExecuteDeleteAfter__(profile, properties, function);
		__inject__(KeycloakHelper.class).deleteRole(profile.getCode());
	}
	
	@Override
	public ProfilePersistence setPrivileges(Profile profile) {
		Collection<ProfilePrivilege> profilePrivileges = __inject__(ProfilePrivilegePersistence.class).readByProfiles(profile);
		if(CollectionHelper.isNotEmpty(profilePrivileges))
			profile.setPrivileges(__inject__(Privileges.class).add(profilePrivileges.stream().map(ProfilePrivilege::getPrivilege).collect(Collectors.toList())));
		return this;
	}
	
	@Override
	public ProfilePersistence exportToKeycloak() {
		Collection<Profile> profiles = read();
		if(profiles != null)
			for(Profile index : profiles)
				__createIntoKeycloak__(index);
		return this;
	}
	
	private void __createIntoKeycloak__(Profile profile) {
		if(profile.getType().getCode().equals(ProfileType.CODE_SYSTEM))
			__inject__(KeycloakHelper.class).createRole(profile.getCode(), profile.getName(),"PROFILE");
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, Profile.FIELD_TYPE)))
				return readByTypesCodes;
		}
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByTypesCodes)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects)) {
				objects = new Object[] {queryContext.getFilterByKeysValue(Profile.FIELD_TYPE)};
			}
			return new Object[]{"codes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
}
