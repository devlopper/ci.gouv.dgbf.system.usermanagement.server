package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=Function.TABLE_NAME)
public class Function extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_CATEGORY) @NotNull private FunctionCategory category;
	
	@Transient private Boolean isProfileCreatableOnCreate;
	
	/**/
	
	@Override
	public Function setIdentifier(String identifier) {
		return (Function) super.setIdentifier(identifier);
	}
	
	@Override
	public Function setCode(String code) {
		return (Function) super.setCode(code);
	}
	
	@Override
	public Function setName(String name) {
		return (Function) super.setName(name);
	}
	
	/**/
	
	public static final String FIELD_CATEGORY = "category";
	
	public static final String TABLE_NAME = "fonction";
	
	public static final String COLUMN_CATEGORY = "categorie";
	
}
