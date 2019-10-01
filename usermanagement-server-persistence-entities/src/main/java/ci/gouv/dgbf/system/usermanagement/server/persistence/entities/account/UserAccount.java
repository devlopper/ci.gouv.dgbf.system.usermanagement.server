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
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.instance.InstanceHelper;
import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profiles;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scopes;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScopes;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privileges;
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
	
	@Transient private Functions functions;
	@Transient private Profiles profiles;
	@Transient private Scopes scopes;
	//@Transient private Profiles systemProfiles;
	@Transient private Privileges privileges;
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
	
	public Functions getFunctions(Boolean injectIfNull) {
		return (Functions) __getInjectIfNull__(FIELD_FUNCTIONS, injectIfNull);
	}
	
	public UserAccount addFunctions(Collection<Function> functions) {
		getFunctions(Boolean.TRUE).add(functions);
		return this;
	}
	
	public UserAccount addFunctions(Function...functions) {
		getFunctions(Boolean.TRUE).add(functions);
		return this;
	}
	
	public UserAccount addFunctionsByCodes(Collection<String> functionsCodes) {
		if(CollectionHelper.isNotEmpty(functionsCodes)) {
			for(String index : functionsCodes)
				addFunctions(__inject__(InstanceHelper.class).getByIdentifierBusiness(Function.class, index));
		}
		return this;
	}
	
	public UserAccount addFunctionsByCodes(String...functionsCodes) {
		return addFunctionsByCodes(CollectionHelper.listOf(functionsCodes));
	}
	
	public Scopes getScopes(Boolean injectIfNull) {
		return (Scopes) __getInjectIfNull__(FIELD_SCOPES, injectIfNull);
	}
	
	public UserAccount addScopes(Collection<Scope> scopes) {
		getScopes(Boolean.TRUE).add(scopes);
		return this;
	}
	
	public UserAccount addScopes(Scope...scopes) {
		getScopes(Boolean.TRUE).add(scopes);
		return this;
	}
	
	public UserAccount addScopesByIdentifiers(Collection<String> identifiers) {
		if(CollectionHelper.isNotEmpty(identifiers)) {
			for(String index : identifiers)
				addScopes(__inject__(InstanceHelper.class).getByIdentifierSystem(Scope.class, index));
		}
		return this;
	}
	
	public UserAccount addScopesByIdentifiers(String...identifiers) {
		return addScopesByIdentifiers(CollectionHelper.listOf(identifiers));
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
			addProfiles(CollectionHelper.listOf(profiles));
		}
		return this;
	}
	
	public Privileges getPrivileges(Boolean injectIfNull) {
		return (Privileges) __getInjectIfNull__(FIELD_PRIVILEGES, injectIfNull);
	}
	
	public UserAccount addPrivileges(Collection<Privilege> privileges) {
		getPrivileges(Boolean.TRUE).add(privileges);
		return this;
	}
	
	public UserAccount addPrivileges(Privilege...privileges) {
		if(__inject__(ArrayHelper.class).isNotEmpty(privileges)) {
			addPrivileges(CollectionHelper.listOf(privileges));
		}
		return this;
	}
	
	public UserAccount addPrivilegesByCodes(Collection<String> privilegesCodes) {
		if(CollectionHelper.isNotEmpty(privilegesCodes)) {
			for(String index : privilegesCodes)
				addPrivileges(__inject__(InstanceHelper.class).getByIdentifierBusiness(Privilege.class, index));
		}
		return this;
	}
	
	public UserAccount addPrivilegesByCodes(String...privilegesCodes) {
		return addPrivilegesByCodes(CollectionHelper.listOf(privilegesCodes));
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
			addFunctionScopes(CollectionHelper.listOf(functionScopes));
		}
		return this;
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_FUNCTIONS = "functions";
	public static final String FIELD_SCOPES = "scopes";
	//public static final String FIELD_SYSTEM_PROFILES = "systemProfiles";
	public static final String FIELD_PROFILES = "profiles";
	public static final String FIELD_PRIVILEGES = "privileges";
	public static final String FIELD_FUNCTION_SCOPES = "functionScopes";
	
	public static final String TABLE_NAME = Account.TABLE_NAME+User.TABLE_NAME;
	
	public static final String COLUMN_USER = User.TABLE_NAME;
	public static final String COLUMN_ACCOUNT = Account.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_USER_ACCOUNT_NAME = COLUMN_USER+COLUMN_ACCOUNT;
	
}
