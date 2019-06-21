package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @Accessors(chain=true)
@Table(name=UserAccountInterim.TABLE_NAME)
public class UserAccountInterim extends AbstractUserAccountInterimCommon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name=COLUMN_FROM_DATE) @NotNull
	private LocalDateTime fromDate;
	
	@Column(name=COLUMN_TO_DATE) @NotNull
	private LocalDateTime toDate;
	
	@Override
	public UserAccountInterim setIdentifier(String identifier) {
		return (UserAccountInterim) super.setIdentifier(identifier);
	}
	
	/**/

	public static final String FIELD_FROM_DATE = "fromDate";
	public static final String FIELD_TO_DATE = "toDate";
	
	public static final String TABLE_NAME = "interim";
	
	public static final String COLUMN_FROM_DATE = "debut";
	public static final String COLUMN_TO_DATE = "fin";
}