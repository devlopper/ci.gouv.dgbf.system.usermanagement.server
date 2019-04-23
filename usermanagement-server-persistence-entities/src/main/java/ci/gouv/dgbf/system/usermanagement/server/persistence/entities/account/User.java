package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=User.TABLE_NAME)
public class User extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	//Names
	@NotNull private String firstName;
	private String lastNames;
	
	//Contacts
	@Column(name=COLUMN_ELECTRONIC_MAIL_ADDRESS) @NotNull private String electronicMailAddress;
	@Column(name=COLUMN_PHONE_NUMBER) private String phoneNumber;
	
	/**/

	@Override
	public User setIdentifier(String identifier) {
		return (User) super.setIdentifier(identifier);
	}
	
	/**/
	
	/**/
	
	public static final String FIELD_FIRST_NAME = "firstName";
	public static final String FIELD_LAST_NAMES = "lastNames";
	public static final String FIELD_ELECTRONIC_MAIL_ADDRESS = "electronicMailAddress";
	public static final String FIELD_PHONE_NUMBER = "phoneNumber";
	
	public static final String TABLE_NAME = "util";
	
	public static final String COLUMN_ELECTRONIC_MAIL_ADDRESS = "adresse_electronique";
	public static final String COLUMN_PHONE_NUMBER = "numero_telephone";
	
}
