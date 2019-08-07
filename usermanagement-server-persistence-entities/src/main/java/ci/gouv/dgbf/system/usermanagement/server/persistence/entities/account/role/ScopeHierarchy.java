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
@Table(name=ScopeHierarchy.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=ScopeHierarchy.UNIQUE_CONSTRAINT_PARENT_CHILD_NAME,columnNames= {ScopeHierarchy.COLUMN_PARENT,ScopeHierarchy.COLUMN_CHILD}
		)})
public class ScopeHierarchy extends AbstractHierarchy<Scope> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public ScopeHierarchy setParent(Scope parent) {
		return (ScopeHierarchy) super.setParent(parent);
	}
	
	@Override
	public ScopeHierarchy setChild(Scope child) {
		return (ScopeHierarchy) super.setChild(child);
	}
	
	public static final String TABLE_NAME = Scope.TABLE_NAME+"hierarchy";
	
	public static final String UNIQUE_CONSTRAINT_PARENT_CHILD_NAME = TABLE_NAME+"_"+AbstractHierarchy.UNIQUE_CONSTRAINT_PARENT_CHILD_NAME;
	
}
