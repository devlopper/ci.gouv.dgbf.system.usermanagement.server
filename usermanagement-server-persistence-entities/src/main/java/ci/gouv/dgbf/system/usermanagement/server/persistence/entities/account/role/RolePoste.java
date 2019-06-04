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
@Table(name=RolePoste.TABLE_NAME)
public class RolePoste extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_FUNCTION) @NotNull private RoleFunction function;
	
	@ManyToOne @JoinColumn(name=COLUMN_MINISTRY) private Ministry ministry;
	@ManyToOne @JoinColumn(name=COLUMN_PROGRAM) private Program program;
	@ManyToOne @JoinColumn(name=COLUMN_ADMINISTRATIVE_UNIT) private AdministrativeUnit administrativeUnit;
	
	/**/
	
	@Override
	public RolePoste setIdentifier(String identifier) {
		return (RolePoste) super.setIdentifier(identifier);
	}
	
	@Override
	public RolePoste setCode(String code) {
		return (RolePoste) super.setCode(code);
	}
	
	@Override
	public RolePoste setName(String name) {
		return (RolePoste) super.setName(name);
	}
	
	/**/
	
	public static final String FIELD_FUNCTION = "fonction";
	public static final String FIELD_MINISTRY = "ministry";
	public static final String FIELD_PROGRAM = "program";
	public static final String FIELD_ADMINISTRATIVE_UNIT = "administrativeUnit";
	
	public static final String TABLE_NAME = "poste";
	
	public static final String COLUMN_FUNCTION = RoleFunction.TABLE_NAME;
	public static final String COLUMN_MINISTRY = Ministry.TABLE_NAME;
	public static final String COLUMN_PROGRAM = Program.TABLE_NAME;
	public static final String COLUMN_ADMINISTRATIVE_UNIT = AdministrativeUnit.TABLE_NAME;
	
}
