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
@Table(name=RolePoste.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=RolePoste.UNIQUE_CONSTRAINT_FUNCTION_ROLE_NAME,columnNames= {RolePoste.FIELD_FUNCTION,RolePoste.COLUMN_ROLE})
})
public class RolePoste extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_FUNCTION) @NotNull private RoleFunction function;
	@ManyToOne @JoinColumn(name=COLUMN_ROLE) @NotNull private Role role;
	
	/**/
	
	@Override
	public RolePoste setIdentifier(String identifier) {
		return (RolePoste) super.setIdentifier(identifier);
	}
	
	/**/
	
	public static final String FIELD_FUNCTION = "fonction";
	public static final String FIELD_ROLE = "role";
	
	public static final String TABLE_NAME = "posterole";
	
	public static final String COLUMN_FUNCTION = "fonction";
	public static final String COLUMN_ROLE = "role";
	
	public static final String UNIQUE_CONSTRAINT_FUNCTION_ROLE_NAME = FIELD_FUNCTION+ "_"+COLUMN_ROLE;
}
