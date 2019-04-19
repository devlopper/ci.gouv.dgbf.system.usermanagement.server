package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCoded;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=Profile.TABLE_NAME)
public class Profile extends AbstractIdentifiedByStringAndCoded implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Profile setCode(String code) {
		return (Profile) super.setCode(code);
	}
	
	/**/

	public static final String TABLE_NAME = "profile";
	
}
