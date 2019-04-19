package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamedAndDescribable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

//@javax.persistence.Entity 
@Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=Role.TABLE_NAME)
public class Role extends AbstractIdentifiedByStringAndCodedAndNamedAndDescribable implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_TYPE) @NotNull private RoleType type;
	
	@Override
	public Role setIdentifier(String identifier) {
		return (Role) super.setIdentifier(identifier);
	}
	
	@Override
	public Role setCode(String code) {
		return (Role) super.setCode(code);
	}
	
	@Override
	public Role setName(String name) {
		return (Role) super.setName(name);
	}
	
	@Override
	public Role setDescription(String description) {
		return (Role) super.setDescription(description);
	}
	
	/**/
	
	public static final String FIELD_TYPE = "type";
	
	public static final String TABLE_NAME = "role";
	
	public static final String COLUMN_TYPE = "type";
	
}
