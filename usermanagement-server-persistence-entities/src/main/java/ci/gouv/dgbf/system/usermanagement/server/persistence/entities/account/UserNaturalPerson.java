package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=UserNaturalPerson.TABLE_NAME)
public class UserNaturalPerson extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_USER) @NotNull private User user;
	
	@Column(name=COLUMN_FIRST_NAME) @NotNull private String firstName;
	@Column(name=COLUMN_LAST_NAMES) private String lastNames;
	@Column(name=COLUMN_IS_MASCULINE) private Boolean isMasculine;
	
	//Transients
	@Transient private String administrativeUnit;
	@Transient private String function;
	
	/**/
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_FIRST_NAME = "firstName";
	public static final String FIELD_LAST_NAMES = "lastNames";
	public static final String FIELD_IS_MASCULINE = "isMasculine";
	
	public static final String TABLE_NAME = User.TABLE_NAME+"_pers";
	
	public static final String COLUMN_USER = User.TABLE_NAME;
	public static final String COLUMN_FIRST_NAME = "nom";
	public static final String COLUMN_LAST_NAMES = "prenoms";
	public static final String COLUMN_IS_MASCULINE = "sexe";
	
}
