package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;

@ApplicationScoped
public class FunctionScopePersistenceImpl extends AbstractPersistenceEntityImpl<FunctionScope> implements FunctionScopePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readWhereNameContains;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readWhereNameContains, "SELECT tuple FROM FunctionScope tuple WHERE lower(tuple.code) LIKE lower(:query) OR lower(tuple.name) LIKE lower(:query)");
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereNameContains)) {
			if(Boolean.TRUE.equals(__inject__(ArrayHelper.class).isEmpty(objects)))
				objects = new Object[] {queryContext.getFilterByKeysValue("__global_query__")};
			return new Object[]{"query", "%"+objects[0]+"%"};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass) && Properties.getFromPath(properties,Properties.QUERY_FILTERS) != null)
			return readWhereNameContains;
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
}
