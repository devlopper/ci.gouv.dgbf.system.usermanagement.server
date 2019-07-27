package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=ProfileType.TABLE_NAME)
public class ProfileType extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	/**/
	
	@Override
	public ProfileType setIdentifier(String identifier) {
		return (ProfileType) super.setIdentifier(identifier);
	}
	
	@Override
	public ProfileType setCode(String code) {
		return (ProfileType) super.setCode(code);
	}
	
	@Override
	public ProfileType setName(String name) {
		return (ProfileType) super.setName(name);
	}
	
	/**/

	public static final String TABLE_NAME = "typ"+Profile.TABLE_NAME;
	
	public static final String CODE_SYSTEM = "SYSTEME";
	public static final String CODE_UTILISATEUR = "UTILISATEUR";
}
