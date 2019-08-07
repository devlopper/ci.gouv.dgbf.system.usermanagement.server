package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;

@ApplicationScoped
public class UserFunctionPersistenceImpl extends AbstractPersistenceEntityImpl<UserFunction> implements UserFunctionPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readByUsersIdentifiers,readByFunctionsIdentifiers,readByFunctionsCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByUsersIdentifiers, String.format("SELECT tuple FROM %s tuple WHERE tuple.user.identifier IN :identifiers", __getTupleName__()));
		addQueryCollectInstances(readByFunctionsIdentifiers, String.format("SELECT tuple FROM %s tuple WHERE tuple.function.identifier IN :identifiers", __getTupleName__()));
		addQueryCollectInstances(readByFunctionsCodes, String.format("SELECT tuple FROM %s tuple WHERE tuple.function.code IN :codes", __getTupleName__()));
	}
	
	@Override
	public Collection<UserFunction> readByFunctionsCodes(Collection<String> functionsCodes) {
		Properties properties = new Properties().setQueryIdentifier(readByFunctionsCodes);
		return __readMany__(properties,____getQueryParameters____(properties,functionsCodes));	
	}
	
	@Override
	public Collection<UserFunction> readByFunctionsCodes(String... functionsCodes) {
		return readByFunctionsCodes(__injectCollectionHelper__().instanciate(functionsCodes));
	}
	
	@Override
	public Collection<UserFunction> readByFunctions(Collection<Function> functions) {
		return __injectCollectionHelper__().isEmpty(functions) ? null : readByFunctionsCodes(functions.stream().map(Function::getCode).collect(Collectors.toList()));
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, UserFunction.FIELD_USER)))
				return readByUsersIdentifiers;
			else if(Boolean.TRUE.equals(__isFilterByKeys__(properties, UserFunction.FIELD_FUNCTION)))
				return readByFunctionsCodes;
		}
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByUsersIdentifiers)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects)) {
				objects = new Object[] {queryContext.getFilterByKeysValue(UserFunction.FIELD_USER)};
			}
			return new Object[]{"identifiers",objects[0]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByFunctionsCodes)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(UserFunction.FIELD_FUNCTION)};
			return new Object[]{"codes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
		
}