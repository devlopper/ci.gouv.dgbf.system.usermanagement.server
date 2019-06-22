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

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=UserAccountRolePoste.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=UserAccountRolePoste.UNIQUE_CONSTRAINT_USER_ACCOUNT_ROLE_POSTE_NAME,columnNames= {UserAccountRolePoste.COLUMN_USER_ACCOUNT,UserAccountRolePoste.COLUMN_ROLE_POSTE}
		)})
public class UserAccountRolePoste extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_USER_ACCOUNT) @NotNull private UserAccount userAccount;
	@ManyToOne @JoinColumn(name=COLUMN_ROLE_POSTE) @NotNull private RolePoste rolePoste;
	private Boolean isInterim;
	
	/**/
	
	public static final String FIELD_USER_ACCOUNT = "userAccount";
	public static final String FIELD_ROLE_POSTE = "rolePoste";
	public static final String FIELD_IS_INTERIM = "isInterim";
	
	public static final String TABLE_NAME = UserAccount.TABLE_NAME+"_"+RolePoste.TABLE_NAME;
	
	public static final String COLUMN_USER_ACCOUNT = UserAccount.TABLE_NAME;
	public static final String COLUMN_ROLE_POSTE = RolePoste.TABLE_NAME;
	public static final String COLUMN_IS_INTERIM = "est_"+UserAccountInterim.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_USER_ACCOUNT_ROLE_POSTE_NAME = COLUMN_USER_ACCOUNT+ "_"+COLUMN_ROLE_POSTE;
	
}
