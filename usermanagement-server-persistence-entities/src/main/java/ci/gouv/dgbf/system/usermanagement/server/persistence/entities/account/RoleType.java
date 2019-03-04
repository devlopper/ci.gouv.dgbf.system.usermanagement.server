package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=RoleType.TABLE_NAME)
public class RoleType extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name=COLUMN_NAME) @NotNull private String name;
	@Column(name=COLUMN_DESCRIPTION) private String description;
	
	@Override
	public RoleType setCode(String code) {
		return (RoleType) super.setCode(code);
	}
	
	/**/
	
	public static final String FIELD_NAME = "name";
	public static final String FIELD_DESCRIPTION = "description";
	
	public static final String TABLE_NAME = "typerole";
	
	public static final String COLUMN_NAME = "nom";
	public static final String COLUMN_DESCRIPTION = "description";
}
