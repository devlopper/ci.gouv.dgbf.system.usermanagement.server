package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractHierarchy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=PrivilegeHierarchy.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=PrivilegeHierarchy.UNIQUE_CONSTRAINT_PARENT_CHILD_NAME,columnNames= {PrivilegeHierarchy.COLUMN_PARENT,PrivilegeHierarchy.COLUMN_CHILD}
		)})
public class PrivilegeHierarchy extends AbstractHierarchy<Privilege> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PrivilegeHierarchy setParent(Privilege parent) {
		return (PrivilegeHierarchy) super.setParent(parent);
	}
	
	@Override
	public PrivilegeHierarchy setChild(Privilege child) {
		return (PrivilegeHierarchy) super.setChild(child);
	}
	
	public static final String TABLE_NAME = Privilege.TABLE_NAME+"hierarchy";
	
	public static final String UNIQUE_CONSTRAINT_PARENT_CHILD_NAME = TABLE_NAME+"_"+AbstractHierarchy.UNIQUE_CONSTRAINT_PARENT_CHILD_NAME;
	
}
