package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileServiceResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileServiceResource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;

@ApplicationScoped
public class ProfileServiceResourcePersistenceImpl extends AbstractPersistenceEntityImpl<ProfileServiceResource> implements ProfileServiceResourcePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByProfilesCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByProfilesCodes, "SELECT tuple FROM ProfileServiceResource tuple WHERE tuple.profile.code IN :profilesCodes");
	}
	
	@Override
	public PersistenceServiceProvider<ProfileServiceResource> create(ProfileServiceResource profileServiceResource, Properties properties) {
		super.create(profileServiceResource, properties);
		__inject__(KeycloakHelper.class).createPermission(profileServiceResource.getProfile(), profileServiceResource.getService(), profileServiceResource.getResource());
		return this;
	}
	
	@Override
	public Collection<ProfileServiceResource> readByProfilesCodes(Collection<String> profilesCodes) {
		Properties properties = new Properties().setQueryIdentifier(readByProfilesCodes);
		return __readMany__(properties,____getQueryParameters____(properties,readByProfilesCodes));	
	}
	
	@Override
	public Collection<ProfileServiceResource> readByProfilesCodes(String... profilesCodes) {
		return readByProfilesCodes(__injectCollectionHelper__().instanciate(profilesCodes));
	}
	
	@Override
	public Collection<ProfileServiceResource> readByProfiles(Collection<Profile> profiles) {
		if(__injectCollectionHelper__().isNotEmpty(profiles))
			return readByProfilesCodes(profiles.stream().map(Profile::getCode).collect(Collectors.toList()));
		return null;
	}
	
	@Override
	public Collection<ProfileServiceResource> readByProfiles(Profile... profiles) {
		return readByProfiles(__injectCollectionHelper__().instanciate(profiles));
	}
	
	@Override
	public PersistenceServiceProvider<ProfileServiceResource> delete(ProfileServiceResource profileServiceResource, Properties properties) {
		__inject__(KeycloakHelper.class).deletePermission(profileServiceResource.getProfile(), profileServiceResource.getService(), profileServiceResource.getResource());
		return super.delete(profileServiceResource, properties);
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByProfilesCodes)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(ProfileFunction.FIELD_PROFILE)};
			return new Object[]{"profilesCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, ProfileFunction.FIELD_PROFILE)))
				return readByProfilesCodes;
		}
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
}
