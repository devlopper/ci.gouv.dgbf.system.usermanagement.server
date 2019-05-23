package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=RoleFunction.TABLE_NAME)
public class RoleFunction extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_CATEGORY) @NotNull private RoleCategory category;
	
	/**/
	
	@Override
	public RoleFunction setIdentifier(String identifier) {
		return (RoleFunction) super.setIdentifier(identifier);
	}
	
	@Override
	public RoleFunction setCode(String code) {
		return (RoleFunction) super.setCode(code);
	}
	
	@Override
	public RoleFunction setName(String name) {
		return (RoleFunction) super.setName(name);
	}
	
	/**/
	
	public static final String FIELD_CATEGORY = "category";
	
	public static final String TABLE_NAME = "fonction";
	
	public static final String COLUMN_CATEGORY = "categorie";
	
}
