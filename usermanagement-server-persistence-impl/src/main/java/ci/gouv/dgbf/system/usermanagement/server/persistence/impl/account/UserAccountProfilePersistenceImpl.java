package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQuery;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;

@ApplicationScoped
public class UserAccountProfilePersistenceImpl extends AbstractPersistenceEntityImpl<UserAccountProfile> implements UserAccountProfilePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readByUserAccount;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByUserAccount, __instanciateQueryReadBy__(UserAccountProfile.FIELD_USER_ACCOUNT));
	}
	
	@Override
	public Collection<UserAccountProfile> readByUserAccount(UserAccount userAccount) {
		Properties properties = new Properties().setQueryIdentifier(readByUserAccount);
		return __readMany__(properties,____getQueryParameters____(properties,userAccount));
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQuery query, Properties properties, Object... objects) {
		if(query.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByUserAccount))
			return new Object[]{UserAccountProfile.FIELD_USER_ACCOUNT, objects[0]};
		return super.__getQueryParameters__(query, properties, objects);
	}
	
}
