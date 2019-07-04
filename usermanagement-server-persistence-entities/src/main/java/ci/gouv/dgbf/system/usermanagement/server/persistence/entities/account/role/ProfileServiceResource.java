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
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=ProfileServiceResource.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=ProfileServiceResource.UNIQUE_CONSTRAINT_PROFILE_SERVICE_RESOURCE_NAME,columnNames= {ProfileServiceResource.COLUMN_PROFILE
				,ProfileServiceResource.COLUMN_SERVICE,ProfileServiceResource.COLUMN_RESOURCE}
		)})
public class ProfileServiceResource extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_PROFILE) @NotNull private Profile profile;
	@ManyToOne @JoinColumn(name=COLUMN_SERVICE) @NotNull private Service service;
	@ManyToOne @JoinColumn(name=COLUMN_RESOURCE) @NotNull private Resource resource;
	
	/**/
	
	public static final String FIELD_PROFILE = "profile";
	public static final String FIELD_SERVICE = "service";
	public static final String FIELD_RESOURCE = "resource";
	
	public static final String TABLE_NAME = Profile.TABLE_NAME+Service.TABLE_NAME+Resource.TABLE_NAME;
	
	public static final String COLUMN_PROFILE = Profile.TABLE_NAME;
	public static final String COLUMN_SERVICE = Service.TABLE_NAME;
	public static final String COLUMN_RESOURCE = Resource.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_PROFILE_SERVICE_RESOURCE_NAME = COLUMN_PROFILE+COLUMN_SERVICE+COLUMN_RESOURCE;
	
}
