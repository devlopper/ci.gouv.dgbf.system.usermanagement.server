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
@Table(name=Account.TABLE_NAME)
public class Account extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name=COLUMN_PASS) @NotNull private String pass;
	
	/**/
	
	@Override
	public Account setIdentifier(String identifier) {
		return (Account) super.setIdentifier(identifier);
	}
	
	/**/
	
	public static final String FIELD_PASS = "pass";
	
	public static final String TABLE_NAME = "compte";
	
	public static final String COLUMN_PASS = "pass";
	
}
