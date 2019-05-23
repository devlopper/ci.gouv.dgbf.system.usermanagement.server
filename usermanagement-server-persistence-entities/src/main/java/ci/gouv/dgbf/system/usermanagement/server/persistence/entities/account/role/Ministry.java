package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @Accessors(chain=true)
@Table(name=Ministry.TABLE_NAME)
public class Ministry extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Ministry setIdentifier(String identifier) {
		return (Ministry) super.setIdentifier(identifier);
	}
	
	/**/

	public static final String TABLE_NAME = "ministere";
	
}
