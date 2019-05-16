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
@Table(name=RoleFunction.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=RoleFunction.UNIQUE_CONSTRAINT_CATEGORY_ROLE_NAME,columnNames= {RoleFunction.FIELD_CATEGORY,RoleFunction.COLUMN_ROLE})
})
public class RoleFunction extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_CATEGORY) @NotNull private RoleCategory category;
	@ManyToOne @JoinColumn(name=COLUMN_ROLE) @NotNull private Role role;
	
	/**/
	
	@Override
	public RoleFunction setIdentifier(String identifier) {
		return (RoleFunction) super.setIdentifier(identifier);
	}
	
	/**/
	
	public static final String FIELD_CATEGORY = "category";
	public static final String FIELD_ROLE = "role";
	
	public static final String TABLE_NAME = "foncrole";
	
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_ROLE = "role";
	
	public static final String UNIQUE_CONSTRAINT_CATEGORY_ROLE_NAME = FIELD_CATEGORY+ "_"+COLUMN_ROLE;
	
}
