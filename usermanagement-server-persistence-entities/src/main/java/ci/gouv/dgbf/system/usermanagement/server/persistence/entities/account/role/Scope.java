package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractIdentifiedByStringAndLinkedByStringAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @Accessors(chain=true)
@Table(name=Scope.TABLE_NAME)
public class Scope extends AbstractIdentifiedByStringAndLinkedByStringAndNamed<Scope> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_TYPE) @NotNull private ScopeType type;
	
	public Scope setTypeFromCode(String code) {
		__setFromBusinessIdentifier__(FIELD_TYPE, code);
		return this;
	}
	
	@Override @JsonbProperty(value="code")
	public Scope setIdentifier(String identifier) {
		return (Scope) super.setIdentifier(identifier);
	}
	
	@Override @JsonbProperty(value="code")
	public String getIdentifier() {
		return super.getIdentifier();
	}
	
	@Override @JsonbProperty(value="uuid")
	public String getLink() {
		return super.getLink();
	}
	
	@Override @JsonbProperty(value="uuid")
	public Scope setLink(String link) {
		return (Scope) super.setLink(link);
	}
	
	@Override @JsonbProperty(value="libelle")
	public String getName() {
		return super.getName();
	}
	
	@Override @JsonbProperty(value="libelle")
	public Scope setName(String name) {
		return (Scope) super.setName(name);
	}
	
	/**/

	public static final String FIELD_TYPE = "type";
	
	public static final String COLUMN_TYPE = "type";
	
	public static final String TABLE_NAME = "dmn";
}
