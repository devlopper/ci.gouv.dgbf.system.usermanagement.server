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
@Table(name=PosteLocationType.TABLE_NAME)
public class PosteLocationType extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	/**/
	
	@Override
	public PosteLocationType setIdentifier(String identifier) {
		return (PosteLocationType) super.setIdentifier(identifier);
	}
	
	@Override
	public PosteLocationType setCode(String code) {
		return (PosteLocationType) super.setCode(code);
	}
	
	@Override
	public PosteLocationType setName(String name) {
		return (PosteLocationType) super.setName(name);
	}
	
	/**/

	public static final String TABLE_NAME = "type_"+PosteLocation.TABLE_NAME;
	
}
