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
@Table(name=RoleCategory.TABLE_NAME)
public class RoleCategory extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	/**/
	
	@Override
	public RoleCategory setIdentifier(String identifier) {
		return (RoleCategory) super.setIdentifier(identifier);
	}
	
	@Override
	public RoleCategory setCode(String code) {
		return (RoleCategory) super.setCode(code);
	}
	
	@Override
	public RoleCategory setName(String name) {
		return (RoleCategory) super.setName(name);
	}
	
	/**/

	public static final String TABLE_NAME = "categrole";
	
}
