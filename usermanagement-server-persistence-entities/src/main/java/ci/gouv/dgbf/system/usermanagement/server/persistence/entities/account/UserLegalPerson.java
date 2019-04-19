package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
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
@Table(name=UserLegalPerson.TABLE_NAME)
public class UserLegalPerson extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_USER) @NotNull private User user;
	@Column(name=COLUMN_NAME) @NotNull private String name;
	
	/**/
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_NAME = "name";
	
	public static final String TABLE_NAME = User.TABLE_NAME+"_sys";
	
	public static final String COLUMN_USER = User.TABLE_NAME;
	public static final String COLUMN_NAME = "nom";
	
}
