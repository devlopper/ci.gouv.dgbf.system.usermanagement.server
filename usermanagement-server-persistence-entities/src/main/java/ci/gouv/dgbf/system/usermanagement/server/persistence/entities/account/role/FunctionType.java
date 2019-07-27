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
@Table(name=FunctionType.TABLE_NAME)
public class FunctionType extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	/**/
	
	@Override
	public FunctionType setIdentifier(String identifier) {
		return (FunctionType) super.setIdentifier(identifier);
	}
	
	@Override
	public FunctionType setCode(String code) {
		return (FunctionType) super.setCode(code);
	}
	
	@Override
	public FunctionType setName(String name) {
		return (FunctionType) super.setName(name);
	}
	
	/**/

	public static final String TABLE_NAME = "typ"+Function.TABLE_NAME;
	
}
