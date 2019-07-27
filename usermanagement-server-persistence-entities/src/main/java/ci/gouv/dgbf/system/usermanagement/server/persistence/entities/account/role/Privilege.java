package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=Privilege.TABLE_NAME)
public class Privilege extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_TYPE) @NotNull private PrivilegeType type;
	@Column(name = COLUMN_URL) private String url;
	@ManyToOne @JoinColumn(name=COLUMN_PARENT) private Privilege parent;
	
	@Override
	public Privilege setIdentifier(String identifier) {
		return (Privilege) super.setIdentifier(identifier);
	}
	
	@Override
	public Privilege setCode(String code) {
		return (Privilege) super.setCode(code);
	}
	
	@Override
	public Privilege setName(String name) {
		return (Privilege) super.setName(name);
	}
	
	/**/
	
	/**/
	
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_URL = "url";
	public static final String FIELD_PARENT = "parent";
	
	public static final String TABLE_NAME = "pvlg";
	
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_URL = "url";
	public static final String COLUMN_PARENT = "parent";
	
}
