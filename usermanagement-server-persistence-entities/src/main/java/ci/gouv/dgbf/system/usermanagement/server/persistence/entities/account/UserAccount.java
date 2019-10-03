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

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.instance.InstanceHelper;
import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FieldContainerFunctionScopes;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FieldContainerFunctions;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FieldContainerPrivileges;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FieldContainerProfiles;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FieldContainerScopes;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=UserAccount.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=UserAccount.UNIQUE_CONSTRAINT_USER_ACCOUNT_NAME,columnNames= {UserAccount.COLUMN_USER,UserAccount.COLUMN_ACCOUNT}
		)})
public class UserAccount extends AbstractIdentifiedByString implements FieldContainerFunctions,FieldContainerProfiles,FieldContainerScopes,FieldContainerFunctionScopes,FieldContainerPrivileges,Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_USER) @NotNull private User user;
	@ManyToOne @JoinColumn(name=COLUMN_ACCOUNT) @NotNull private Account account;
	
	@Transient private Collection<Function> functions;
	@Transient private Collection<Profile> profiles;
	@Transient private Collection<Scope> scopes;
	//@Transient private Profiles systemProfiles;
	@Transient private Collection<Privilege> privileges;
	@Transient private Collection<FunctionScope> functionScopes;
	@Transient private Boolean isProfileCreatableOnCreate;
	@Transient private Boolean isNotifiableByMail,isPersistToKeycloakOnCreate;
	
	/**/
	
	/**/
	
	@Override
	public UserAccount setIdentifier(String identifier) {
		return (UserAccount) super.setIdentifier(identifier);
	}
	
	public User getUser(Boolean injectIfNull) {
		if(user == null && Boolean.TRUE.equals(injectIfNull))
			user = new User();
		return user;
	}
	
	public Account getAccount(Boolean injectIfNull) {
		if(account == null && Boolean.TRUE.equals(injectIfNull))
			account = new Account();
		return account;
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
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	//public static final String FIELD_SYSTEM_PROFILES = "systemProfiles";
	
	public static final String TABLE_NAME = Account.TABLE_NAME+User.TABLE_NAME;
	
	public static final String COLUMN_USER = User.TABLE_NAME;
	public static final String COLUMN_ACCOUNT = Account.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_USER_ACCOUNT_NAME = COLUMN_USER+COLUMN_ACCOUNT;
	
}
