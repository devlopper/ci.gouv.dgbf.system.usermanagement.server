package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeProfile;

@ApplicationScoped
public class ScopeTypeProfilePersistenceImpl extends AbstractPersistenceEntityImpl<ScopeTypeProfile> implements ScopeTypeProfilePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readByScopeTypesCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByScopeTypesCodes, "SELECT scopeTypeProfile FROM ScopeTypeProfile scopeTypeProfile WHERE scopeTypeProfile.scopeType.code IN :scopeTypesCodes");
	}
	
	@Override
	public Collection<ScopeTypeProfile> readByScopeTypesCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByScopeTypesCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}

	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByScopeTypesCodes)) {
			return new Object[]{"scopeTypesCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}
