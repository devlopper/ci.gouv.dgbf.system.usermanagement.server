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
@Table(name=FunctionCategory.TABLE_NAME)
public class FunctionCategory extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	/**/
	
	@Override
	public FunctionCategory setIdentifier(String identifier) {
		return (FunctionCategory) super.setIdentifier(identifier);
	}
	
	@Override
	public FunctionCategory setCode(String code) {
		return (FunctionCategory) super.setCode(code);
	}
	
	@Override
	public FunctionCategory setName(String name) {
		return (FunctionCategory) super.setName(name);
	}
	
	/**/

	public static final String TABLE_NAME = "categ"+Function.TABLE_NAME;
	
}
