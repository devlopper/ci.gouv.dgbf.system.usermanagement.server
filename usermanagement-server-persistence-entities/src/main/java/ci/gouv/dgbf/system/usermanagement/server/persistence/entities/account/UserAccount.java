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

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePostes;
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
	@Transient private RolePostes rolePostes;
	//@Transient private Collection<RolePoste> rolePostes;
	@Transient private Boolean notifyByMail;
	
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
	
	public RolePostes getRolePostes(Boolean injectIfNull) {
		return (RolePostes) __getInjectIfNull__(FIELD_ROLE_POSTES, injectIfNull);
	}
	
	public UserAccount addRolePostes(Collection<RolePoste> rolePostes) {
		getRolePostes(Boolean.TRUE).add(rolePostes);
		return this;
	}
	
	public UserAccount addRolePostes(RolePoste...rolePostes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(rolePostes)) {
			addRolePostes(__inject__(CollectionHelper.class).instanciate(rolePostes));
		}
		return this;
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_ROLE_POSTES = "rolePostes";
	
	public static final String TABLE_NAME = Account.TABLE_NAME+"_"+User.TABLE_NAME;
	
	public static final String COLUMN_USER = User.TABLE_NAME;
	public static final String COLUMN_ACCOUNT = Account.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_USER_ACCOUNT_NAME = COLUMN_USER+ "_"+COLUMN_ACCOUNT;
	
}
