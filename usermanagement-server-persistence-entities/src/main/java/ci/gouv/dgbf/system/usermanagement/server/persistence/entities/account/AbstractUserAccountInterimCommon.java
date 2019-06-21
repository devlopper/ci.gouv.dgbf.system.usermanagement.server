package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Access(AccessType.FIELD)
@MappedSuperclass
@Getter @Setter @Accessors(chain=true)
public abstract class AbstractUserAccountInterimCommon extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_USER_ACCOUNT) @NotNull
	private UserAccount userAccount;
	
	@ManyToOne @JoinColumn(name=COLUMN_INTERIM) @NotNull
	private UserAccount interim;
	
	/**/

	public static final String FIELD_USER_ACCOUNT = "userAccount";
	public static final String FIELD_INTERIM = "interim";
	
	public static final String COLUMN_USER_ACCOUNT = UserAccount.TABLE_NAME;
	public static final String COLUMN_INTERIM = "interimaire";
}