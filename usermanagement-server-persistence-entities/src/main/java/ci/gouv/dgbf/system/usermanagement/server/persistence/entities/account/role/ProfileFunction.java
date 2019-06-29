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

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=ProfileFunction.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=ProfileFunction.UNIQUE_CONSTRAINT_PROFILE_FUNCTION_NAME,columnNames= {ProfileFunction.COLUMN_PROFILE,ProfileFunction.COLUMN_FUNCTION}
		)})
public class ProfileFunction extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_PROFILE) @NotNull private Profile profile;
	
	@ManyToOne @JoinColumn(name=COLUMN_FUNCTION) @NotNull private Function function;
	
	/**/
	
	@Override
	public ProfileFunction setIdentifier(String identifier) {
		return (ProfileFunction) super.setIdentifier(identifier);
	}
	
	/**/
	
	public static final String FIELD_PROFILE = "profile";
	public static final String FIELD_FUNCTION = "function";
	
	public static final String TABLE_NAME = Profile.TABLE_NAME+"_"+Function.TABLE_NAME;
	
	public static final String COLUMN_PROFILE = Profile.TABLE_NAME;
	public static final String COLUMN_FUNCTION = Function.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_PROFILE_FUNCTION_NAME = COLUMN_PROFILE+ "_"+COLUMN_FUNCTION;
}
