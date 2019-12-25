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

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=ScopeTypeProfile.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=ScopeTypeProfile.UNIQUE_CONSTRAINT_SCOPE_TYPE_PROFILE_NAME,columnNames= {ScopeTypeProfile.COLUMN_SCOPE_TYPE,ScopeTypeProfile.COLUMN_PROFILE}
		)}) @NoArgsConstructor
public class ScopeTypeProfile extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_SCOPE_TYPE) private ScopeType scopeType;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_PROFILE) private Profile profile;
	@NotNull @Column(name = COLUMN_PROFILE_OF_TYPE_USER_CREATABLE_ON_SCOPE_CREATE) private Boolean profileOfTypeUserCreatableOnScopeCreate;
	
	/**/
	
	public ScopeTypeProfile(ScopeType scopeType,Profile profile,Boolean profileOfTypeUserCreatableOnScopeCreate) {
		this.scopeType = scopeType;
		this.profile = profile;
		this.profileOfTypeUserCreatableOnScopeCreate = profileOfTypeUserCreatableOnScopeCreate;
	}
	
	public ScopeTypeProfile setScopeTypeFromCode(String code) {
		if(StringHelper.isBlank(code))
			return this;
		this.scopeType = InstanceGetter.getInstance().getByBusinessIdentifier(ScopeType.class, code);
		return this;
	}
	
	public ScopeTypeProfile setProfileFromCode(String code) {
		if(StringHelper.isBlank(code))
			return this;
		this.profile = InstanceGetter.getInstance().getByBusinessIdentifier(Profile.class, code);
		return this;
	}
	
	/**/

	public static final String FIELD_SCOPE_TYPE = "scopeType";
	public static final String FIELD_PROFILE = "profile";
	public static final String FIELD_PROFILE_OF_TYPE_USER_CREATABLE_ON_SCOPE_CREATE = "profileOfTypeUserCreatableOnScopeCreate";
	
	public static final String COLUMN_SCOPE_TYPE = ScopeType.TABLE_NAME;
	public static final String COLUMN_PROFILE = Profile.TABLE_NAME;
	public static final String COLUMN_PROFILE_OF_TYPE_USER_CREATABLE_ON_SCOPE_CREATE = "creer_auto";
	
	public static final String TABLE_NAME = ScopeType.TABLE_NAME+Profile.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_SCOPE_TYPE_PROFILE_NAME = COLUMN_SCOPE_TYPE+COLUMN_PROFILE;
}
