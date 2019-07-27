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
@Table(name=ProfilePrivilege.TABLE_NAME,uniqueConstraints= {
		@UniqueConstraint(name=ProfilePrivilege.UNIQUE_CONSTRAINT_PROFILE_PRIVILEGE_NAME,columnNames= {ProfilePrivilege.COLUMN_PROFILE,ProfilePrivilege.COLUMN_PRIVILEGE}
		)})
public class ProfilePrivilege extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_PROFILE) @NotNull private Profile profile;
	
	@ManyToOne @JoinColumn(name=COLUMN_PRIVILEGE) @NotNull private Privilege privilege;
	
	/**/
	
	@Override
	public ProfilePrivilege setIdentifier(String identifier) {
		return (ProfilePrivilege) super.setIdentifier(identifier);
	}
	
	public ProfilePrivilege setProfileFromCode(String code) {
		setProfile(__getFromBusinessIdentifier__(Profile.class, code));
		return this;
	}
	
	public ProfilePrivilege setPrivilegeFromCode(String code) {
		setPrivilege(__getFromBusinessIdentifier__(Privilege.class, code));
		return this;
	}
	
	/**/
	
	public static final String FIELD_PROFILE = "profile";
	public static final String FIELD_PRIVILEGE = "privilege";
	
	public static final String TABLE_NAME = Profile.TABLE_NAME+Privilege.TABLE_NAME;
	
	public static final String COLUMN_PROFILE = Profile.TABLE_NAME;
	public static final String COLUMN_PRIVILEGE = Privilege.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_PROFILE_PRIVILEGE_NAME = COLUMN_PROFILE+COLUMN_PRIVILEGE;
}
