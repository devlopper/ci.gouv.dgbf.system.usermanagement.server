package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=UserAccountScope.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=UserAccountScope.UNIQUE_CONSTRAINT_USER_ACCOUNT_SCOPE_NAME,columnNames= {UserAccountScope.COLUMN_USER_ACCOUNT,UserAccountScope.COLUMN_SCOPE}
		)})
public class UserAccountScope extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_USER_ACCOUNT) @NotNull private UserAccount userAccount;
	@ManyToOne @JoinColumn(name=COLUMN_SCOPE) @NotNull private Scope scope;
	
	/**/
	
	public UserAccountScope setScopeFromIdentifier(String identifier) {
		setFromSystemIdentifier(FIELD_SCOPE, identifier);
		return this;
	}
	
	/**/
	
	public static final String FIELD_USER_ACCOUNT = "userAccount";
	public static final String FIELD_SCOPE = "scope";
	
	public static final String TABLE_NAME = UserAccount.TABLE_NAME+Scope.TABLE_NAME;
	
	public static final String COLUMN_USER_ACCOUNT = UserAccount.TABLE_NAME;
	public static final String COLUMN_SCOPE = Scope.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_USER_ACCOUNT_SCOPE_NAME = COLUMN_USER_ACCOUNT+COLUMN_SCOPE;
	
}
