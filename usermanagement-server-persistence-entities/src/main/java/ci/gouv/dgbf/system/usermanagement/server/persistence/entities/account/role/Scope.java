package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractIdentifiedByStringAndLinkedByStringAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @Accessors(chain=true)
@Table(name=Scope.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=Scope.UNIQUE_CONSTRAINT_TYPE_CODE_NAME,columnNames= {Scope.COLUMN_TYPE,Scope.COLUMN_CODE}
		)})
public class Scope extends AbstractIdentifiedByStringAndLinkedByStringAndNamed<Scope> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne @JoinColumn(name=COLUMN_TYPE) @NotNull private ScopeType type;
	@Column(name=COLUMN_CODE) @NotNull private String code;
	
	public Scope setTypeFromCode(String code) {
		__setFromBusinessIdentifier__(FIELD_TYPE, code);
		return this;
	}
	
	public Scope setIdentifier(String identifier) {
		return (Scope) super.setIdentifier(identifier);
	}
	
	public Scope setName(String name) {
		return (Scope) super.setName(name);
	}
	
	/**/

	@Override
	public String toString() {
		return type+":"+code+":"+name;
	}
	
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_CODE = "code";
	
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_CODE = "code";
	
	public static final String TABLE_NAME = "dmn";
	
	public static final String UNIQUE_CONSTRAINT_TYPE_CODE_NAME = COLUMN_TYPE+COLUMN_CODE;
}
