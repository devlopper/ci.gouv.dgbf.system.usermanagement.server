package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.__kernel__.persistence.query.QueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.query.ReadByScopes;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;

@ApplicationScoped
public class FunctionScopePersistenceImpl extends AbstractPersistenceEntityImpl<FunctionScope> implements FunctionScopePersistence,ReadByScopes<FunctionScope>,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByScopesCodes,readByFunctionCodeByScopeCode,readWhereNameContains;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByScopesCodes, "SELECT tuple FROM FunctionScope tuple WHERE tuple.scope.code IN :scopesCodes");
		addQueryCollectInstances(readByFunctionCodeByScopeCode, "SELECT tuple FROM FunctionScope tuple WHERE tuple.function.code = :functionCode AND tuple.scope.code = :scopeCode");
		addQueryCollectInstances(readWhereNameContains, "SELECT tuple FROM FunctionScope tuple WHERE lower(tuple.code) LIKE lower(:query) OR lower(tuple.name) LIKE lower(:query)");
	}
	
	@Override
	public FunctionScope readByFunctionCodeByScopeCode(String functionCode, String scopeCode, Properties properties) {
		if(StringHelper.isBlank(functionCode) || StringHelper.isBlank(scopeCode))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByFunctionCodeByScopeCode);
		return __readOne__(properties, ____getQueryParameters____(properties,functionCode,scopeCode));
	}
	
	@Override
	public Collection<FunctionScope> readByScopesCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByScopesCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass) && Properties.getFromPath(properties,Properties.QUERY_FILTERS) != null)
			return readWhereNameContains;
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
	@Override
	protected Object[] __getQueryParameters__(QueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByScopesCodes)) {
			return new Object[]{"scopesCodes",objects[0]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByFunctionCodeByScopeCode)) {
			return new Object[]{"functionCode",objects[0],"scopeCode",objects[1]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereNameContains)) {
			if(Boolean.TRUE.equals(__inject__(ArrayHelper.class).isEmpty(objects)))
				objects = new Object[] {queryContext.getFilterByKeysValue("__global_query__")};
			return new Object[]{"query", "%"+objects[0]+"%"};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
}
