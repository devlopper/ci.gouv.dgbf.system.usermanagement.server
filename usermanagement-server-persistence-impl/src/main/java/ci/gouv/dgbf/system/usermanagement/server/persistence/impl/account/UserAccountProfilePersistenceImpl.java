package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserAccountProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

@ApplicationScoped
public class UserAccountProfilePersistenceImpl extends AbstractPersistenceEntityImpl<UserAccountProfile> implements UserAccountProfilePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	private String readByUserAccount,readByProfilesCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByUserAccount, __instanciateQueryReadBy__(UserAccountProfile.FIELD_USER_ACCOUNT));
		addQueryCollectInstances(readByProfilesCodes, "SELECT tuple FROM UserAccountProfile tuple WHERE tuple.profile.code IN :codes");
	}
	
	@Override
	public Collection<UserAccountProfile> readByUserAccount(UserAccount userAccount) {
		Properties properties = new Properties().setQueryIdentifier(readByUserAccount);
		return __readMany__(properties,____getQueryParameters____(properties,userAccount));
	}
	
	@Override
	public Collection<UserAccountProfile> readByProfilesCodes(Collection<String> profileCodes) {
		Properties properties = new Properties().setQueryIdentifier(readByProfilesCodes);
		return __readMany__(properties,____getQueryParameters____(properties,profileCodes));	
	}
	
	@Override
	public Collection<UserAccountProfile> readByProfilesCodes(String... profileCodes) {
		return readByProfilesCodes(__injectCollectionHelper__().instanciate(profileCodes));
	}

	@Override
	public Collection<UserAccountProfile> readByProfiles(Collection<Profile> profiles) {
		if(__injectCollectionHelper__().isNotEmpty(profiles))
			return readByProfilesCodes(profiles.stream().map(Profile::getCode).collect(Collectors.toList()));
		return null;
	}
	
	public Collection<UserAccountProfile> readByProfiles(Profile...profiles) {
		return readByProfiles(__injectCollectionHelper__().instanciate(profiles));
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByUserAccount))
			return new Object[]{UserAccountProfile.FIELD_USER_ACCOUNT, objects[0]};
		
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByProfilesCodes)) {
			if(__inject__(ArrayHelper.class).isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(UserAccountProfile.FIELD_PROFILE)};
			return new Object[]{"profileCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
}
