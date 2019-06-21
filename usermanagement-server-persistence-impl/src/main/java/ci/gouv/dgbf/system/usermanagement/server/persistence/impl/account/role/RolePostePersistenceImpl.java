package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.PersistenceServiceProvider;
import org.cyk.utility.server.persistence.query.PersistenceQuery;
import org.cyk.utility.server.persistence.query.PersistenceQueryRepository;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;

@ApplicationScoped
public class RolePostePersistenceImpl extends AbstractRolePostePersistenceImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String readWhereNameContains;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readWhereNameContains, "SELECT tuple FROM RolePoste tuple WHERE lower(tuple.code) LIKE lower(:query) OR lower(tuple.name) LIKE lower(:query)");
	}
	
	@Override
	public PersistenceServiceProvider<RolePoste> create(RolePoste rolePoste, Properties properties) {
		super.create(rolePoste, properties);
		//__inject__(KeycloakHelper.class).createRole(rolePoste.getCode(), rolePoste.getName(), "POSTE",rolePoste.getFunction().getCode());
		return this;
	}
	
	@Override
	public PersistenceServiceProvider<RolePoste> delete(RolePoste rolePoste, Properties properties) {
		super.delete(rolePoste, properties);
		//__inject__(KeycloakHelper.class).deleteRole(rolePoste.getCode());
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
}
