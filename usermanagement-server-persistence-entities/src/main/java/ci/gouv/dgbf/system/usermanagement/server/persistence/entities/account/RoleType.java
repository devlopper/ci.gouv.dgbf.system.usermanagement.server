package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamedAndDescribable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=RoleType.TABLE_NAME)
public class RoleType extends AbstractIdentifiedByStringAndCodedAndNamedAndDescribable implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_PARENT) private RoleType parent;
	
	@Override
	public RoleType setCode(String code) {
		return (RoleType) super.setCode(code);
	}
	
	@Override
	public RoleType setName(String name) {
		return (RoleType) super.setName(name);
	}
	
	@Override
	public RoleType setDescription(String description) {
		return (RoleType) super.setDescription(description);
	}
	
	/**/
	
	public static final String FIELD_PARENT = "parent";
	
	public static final String TABLE_NAME = "typerole";
	
	public static final String COLUMN_PARENT = "parent";
}
