package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndLinkedByStringAndNamed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @Accessors(chain=true)
@Table(name=Scope.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=Scope.UNIQUE_CONSTRAINT_IDENTIFIER_TYPE,columnNames= {Scope.COLUMN_IDENTIFIER,Scope.COLUMN_TYPE})
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Scope extends AbstractIdentifiedByStringAndLinkedByStringAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_TYPE) @NotNull private ScopeType type;
	
	@Override
	public Scope setIdentifier(String identifier) {
		return (Scope) super.setIdentifier(identifier);
	}
	
	@Override @JsonProperty(value="uuid")
	public String getLink() {
		return super.getLink();
	}
	
	@Override @JsonProperty(value="libelleCourt")
	public String getName() {
		return super.getName();
	}
	
	/**/

	public static final String FIELD_TYPE = "type";
	
	public static final String COLUMN_TYPE = "type";
	
	public static final String TABLE_NAME = "dmn";
	
	public static final String UNIQUE_CONSTRAINT_IDENTIFIER_TYPE = COLUMN_IDENTIFIER+COLUMN_TYPE;
}
