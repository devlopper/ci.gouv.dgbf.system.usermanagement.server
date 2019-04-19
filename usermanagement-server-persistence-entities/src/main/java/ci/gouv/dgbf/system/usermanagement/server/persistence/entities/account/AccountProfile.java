package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=AccountProfile.TABLE_NAME)
public class AccountProfile extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_ACCOUNT) @NotNull private Account account;
	@ManyToOne @JoinColumn(name=COLUMN_PROFILE) @NotNull private Profile profile;
	
	/**/
	
	/**/
	
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_PROFILE = "profile";
	
	public static final String TABLE_NAME = Account.TABLE_NAME+"_"+Profile.TABLE_NAME;
	
	public static final String COLUMN_ACCOUNT = "account";
	public static final String COLUMN_PROFILE = "profile";
	
}
