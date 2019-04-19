package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

//@javax.persistence.Entity 
@Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=RoleCategory.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=RoleCategory.UNIQUE_CONSTRAINT_ROLE_NAME,columnNames= {RoleCategory.FIELD_ROLE})
})
public class RoleCategory extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_ROLE,unique=true) @NotNull private Role role;
	
	/**/
	
	@Override
	public RoleCategory setIdentifier(String identifier) {
		return (RoleCategory) super.setIdentifier(identifier);
	}
	
	/**/
	
	public static final String FIELD_ROLE = "role";
	
	public static final String TABLE_NAME = "categrole";
	
	public static final String COLUMN_ROLE = "role";
	
	public static final String UNIQUE_CONSTRAINT_ROLE_NAME = COLUMN_ROLE;
	
}
