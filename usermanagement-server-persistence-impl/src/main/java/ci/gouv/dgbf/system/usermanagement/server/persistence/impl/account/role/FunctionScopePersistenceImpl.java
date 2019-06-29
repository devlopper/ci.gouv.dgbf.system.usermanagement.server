package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.server.persistence.query.PersistenceQuery;
import org.cyk.utility.server.persistence.query.PersistenceQueryRepository;

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
	public PersistenceServiceProvider<FunctionScope> create(FunctionScope functionScope, Properties properties) {
		super.create(functionScope, properties);
		//__inject__(KeycloakHelper.class).createRole(functionScope.getCode(), functionScope.getName(), "POSTE",functionScope.getFunction().getCode());
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<FunctionScope> delete(FunctionScope functionScope, Properties properties) {
		super.delete(functionScope, properties);
		//__inject__(KeycloakHelper.class).deleteRole(functionScope.getCode());
		return this;
	}
	
	@SuppressWarnings("unchecked")
	protected Object[] __getQueryParameters__(String queryIdentifier,Properties properties,Object...objects){
		PersistenceQuery persistenceQuery = __inject__(PersistenceQueryRepository.class).getBySystemIdentifier(queryIdentifier);
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereNameContains,queryIdentifier)) {
			if(Boolean.TRUE.equals(__inject__(ArrayHelper.class).isEmpty(objects)))
				objects = new Object[] {__injectCollectionHelper__().getFirst((Collection<String>) Properties.getFromPath(properties,Properties.QUERY_FILTERS))};
			return new Object[]{"query", "%"+objects[0]+"%"};
		}
		return super.__getQueryParameters__(queryIdentifier,properties, objects);
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> functionClass, Properties properties, Object... parameters) {
		if(PersistenceFunctionReader.class.equals(functionClass) && Properties.getFromPath(properties,Properties.QUERY_FILTERS) != null)
			return readWhereNameContains;
		return super.__getQueryIdentifier__(functionClass, properties, parameters);
	}
	
	@Override
	public Class<FunctionScope> getEntityClass() {
		return FunctionScope.class;
	}
}
