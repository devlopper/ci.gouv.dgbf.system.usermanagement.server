package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.__kernel__.persistence.query.QueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfileFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;

@ApplicationScoped
public class ProfileFunctionPersistenceImpl extends AbstractPersistenceEntityImpl<ProfileFunction> implements ProfileFunctionPersistence, Serializable {
	private static final long serialVersionUID = 1L;

	private String readByFunctionCodes,readByProfileCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByFunctionCodes, "SELECT tuple FROM ProfileFunction tuple WHERE tuple.function.code IN :functionCodes");
		addQueryCollectInstances(readByProfileCodes, "SELECT tuple FROM ProfileFunction tuple WHERE tuple.profile.code IN :profileCodes");
	}
	
	@Override
	public Collection<ProfileFunction> readByFunctionCodes(Collection<String> functionCodes) {
		Properties properties = new Properties().setQueryIdentifier(readByFunctionCodes);
		return __readMany__(properties,____getQueryParameters____(properties,functionCodes));	
	}
	
	@Override
	public Collection<ProfileFunction> readByFunctionCodes(String... functionCodes) {
		return readByFunctionCodes(CollectionHelper.listOf(functionCodes));
	}
	
	@Override
	public Collection<ProfileFunction> readByFunctions(Collection<Function> functions) {
		if(CollectionHelper.isNotEmpty(functions))
			return readByFunctionCodes(functions.stream().map(Function::getCode).collect(Collectors.toList()));
		return null;
	}
	
	@Override
	public Collection<ProfileFunction> readByProfileCodes(Collection<String> profileCodes) {
		Properties properties = new Properties().setQueryIdentifier(readByProfileCodes);
		return __readMany__(properties,____getQueryParameters____(properties,profileCodes));	
	}
	
	@Override
	public Collection<ProfileFunction> readByProfileCodes(String... profileCodes) {
		return readByProfileCodes(CollectionHelper.listOf(profileCodes));
	}

	@Override
	public Collection<ProfileFunction> readByProfiles(Collection<Profile> profiles) {
		if(CollectionHelper.isNotEmpty(profiles))
			return readByProfileCodes(profiles.stream().map(Profile::getCode).collect(Collectors.toList()));
		return null;
	}
	
	@Override
	protected Object[] __getQueryParameters__(QueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByFunctionCodes)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects)) {
				objects = new Object[] {queryContext.getFilterByKeysValue(ProfileFunction.FIELD_FUNCTION)};
			}
			return new Object[]{"functionCodes",objects[0]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByProfileCodes)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(ProfileFunction.FIELD_PROFILE)};
			return new Object[]{"profileCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, ProfileFunction.FIELD_PROFILE)))
				return readByProfileCodes;
			else if(Boolean.TRUE.equals(__isFilterByKeys__(properties, ProfileFunction.FIELD_FUNCTION)))
				return readByFunctionCodes;
		}
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}

}
