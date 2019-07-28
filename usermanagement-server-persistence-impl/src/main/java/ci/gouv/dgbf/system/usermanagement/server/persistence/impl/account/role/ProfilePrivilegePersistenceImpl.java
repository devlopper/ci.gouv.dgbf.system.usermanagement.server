package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePrivilegePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;

@ApplicationScoped
public class ProfilePrivilegePersistenceImpl extends AbstractPersistenceEntityImpl<ProfilePrivilege> implements ProfilePrivilegePersistence,Serializable {
	private static final long serialVersionUID = 1L;

private String readByProfileCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByProfileCodes, "SELECT tuple FROM ProfilePrivilege tuple WHERE tuple.profile.code IN :profileCodes");
	}
	
	@Override
	public Collection<ProfilePrivilege> readByProfileCodes(Collection<String> profileCodes) {
		Properties properties = new Properties().setQueryIdentifier(readByProfileCodes);
		return __readMany__(properties,____getQueryParameters____(properties,profileCodes));	
	}
	
	@Override
	public Collection<ProfilePrivilege> readByProfileCodes(String... profileCodes) {
		return readByProfileCodes(__injectCollectionHelper__().instanciate(profileCodes));
	}

	@Override
	public Collection<ProfilePrivilege> readByProfiles(Collection<Profile> profiles) {
		if(__injectCollectionHelper__().isNotEmpty(profiles))
			return readByProfileCodes(profiles.stream().map(Profile::getCode).collect(Collectors.toList()));
		return null;
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByProfileCodes)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(ProfilePrivilege.FIELD_PROFILE)};
			return new Object[]{"profileCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, ProfilePrivilege.FIELD_PROFILE)))
				return readByProfileCodes;
		}
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}

}
