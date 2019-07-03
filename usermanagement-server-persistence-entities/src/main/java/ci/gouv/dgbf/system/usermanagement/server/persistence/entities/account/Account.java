package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=Account.TABLE_NAME)
public class Account extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient private String pass;
	
	/**/
	
	@Override
	public Account setIdentifier(String identifier) {
		return (Account) super.setIdentifier(identifier);
	}
	
	/**/
	
	public static final String TABLE_NAME = "cpt";
	
}
