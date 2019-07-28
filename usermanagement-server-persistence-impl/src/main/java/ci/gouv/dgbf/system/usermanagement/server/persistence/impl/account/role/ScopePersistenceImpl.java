package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.query.PersistenceQuery;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

@ApplicationScoped
public class ScopePersistenceImpl extends AbstractPersistenceEntityImpl<Scope> implements ScopePersistence,Serializable {
	private static final long serialVersionUID = 1L;

private String readWhereIdentifierContains;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		//OR lower(tuple.type.name) LIKE lower(:query)
		addQueryCollectInstances(readWhereIdentifierContains, "SELECT tuple FROM Scope tuple WHERE lower(tuple.identifier) LIKE lower(:query)");
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQuery query, Properties properties, Object... objects) {
		if(query.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereIdentifierContains)) {
			if(Boolean.TRUE.equals(__inject__(ArrayHelper.class).isEmpty(objects)))
				objects = new Object[] {__injectCollectionHelper__().getFirst((Collection<String>) Properties.getFromPath(properties,Properties.QUERY_FILTERS))};
			return new Object[]{"query", "%"+objects[0]+"%"};
		}
		return super.__getQueryParameters__(query, properties, objects);
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass) && Properties.getFromPath(properties,Properties.QUERY_FILTERS) != null)
			return readWhereIdentifierContains;
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
}
