package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profiles;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScopes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=UserAccount.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=UserAccount.UNIQUE_CONSTRAINT_USER_ACCOUNT_NAME,columnNames= {UserAccount.COLUMN_USER,UserAccount.COLUMN_ACCOUNT}
		)})
public class UserAccount extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_USER) @NotNull private User user;
	@ManyToOne @JoinColumn(name=COLUMN_ACCOUNT) @NotNull private Account account;
	
	@Transient private Profiles profiles;
	@Transient private FunctionScopes functionScopes;
	@Transient private Boolean isProfileCreatableOnCreate;
	@Transient private Boolean isNotifiableByMail,isPersistToKeycloakOnCreate;
	
	/**/
	
	/**/
	
	@Override
	public UserAccount setIdentifier(String identifier) {
		return (UserAccount) super.setIdentifier(identifier);
	}
	
	public User getUser(Boolean injectIfNull) {
		return (User) __getInjectIfNull__(FIELD_USER, injectIfNull);
	}
	
	public Account getAccount(Boolean injectIfNull) {
		return (Account) __getInjectIfNull__(FIELD_ACCOUNT, injectIfNull);
	}
	
	public Profiles getProfiles(Boolean injectIfNull) {
		return (Profiles) __getInjectIfNull__(FIELD_PROFILES, injectIfNull);
	}
	
	public UserAccount addProfiles(Collection<Profile> profiles) {
		getProfiles(Boolean.TRUE).add(profiles);
		return this;
	}
	
	public UserAccount addProfiles(Profile...profiles) {
		if(__inject__(ArrayHelper.class).isNotEmpty(profiles)) {
			addProfiles(__inject__(CollectionHelper.class).instanciate(profiles));
		}
		return this;
	}
	
	public FunctionScopes getFunctionScopes(Boolean injectIfNull) {
		return (FunctionScopes) __getInjectIfNull__(FIELD_FUNCTION_SCOPES, injectIfNull);
	}
	
	public UserAccount addFunctionScopes(Collection<FunctionScope> functionScopes) {
		getFunctionScopes(Boolean.TRUE).add(functionScopes);
		return this;
	}
	
	public UserAccount addFunctionScopes(FunctionScope...functionScopes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionScopes)) {
			addFunctionScopes(__inject__(CollectionHelper.class).instanciate(functionScopes));
		}
		return this;
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_PROFILES = "profiles";
	public static final String FIELD_FUNCTION_SCOPES = "functionScopes";
	
	public static final String TABLE_NAME = Account.TABLE_NAME+User.TABLE_NAME;
	
	public static final String COLUMN_USER = User.TABLE_NAME;
	public static final String COLUMN_ACCOUNT = Account.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_USER_ACCOUNT_NAME = COLUMN_USER+COLUMN_ACCOUNT;
	
}
