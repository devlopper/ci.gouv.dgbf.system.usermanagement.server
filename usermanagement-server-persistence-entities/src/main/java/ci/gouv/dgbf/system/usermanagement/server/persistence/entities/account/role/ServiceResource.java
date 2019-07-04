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

@Getter @Setter @Accessors(chain=true) 
@Entity @Access(AccessType.FIELD) @Table(name=ServiceResource.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=ServiceResource.UNIQUE_CONSTRAINT_SERVICE_RESOURCE_NAME,columnNames= {ServiceResource.COLUMN_SERVICE,ServiceResource.COLUMN_RESOURCE}
		)})
public class ServiceResource extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_SERVICE) @NotNull private Service service;
	@ManyToOne @JoinColumn(name=COLUMN_RESOURCE) @NotNull private Resource resource;
	
	@Override
	public ServiceResource setIdentifier(String identifier) {
		return (ServiceResource) super.setIdentifier(identifier);
	}
	
	/**/
	
	public static final String FIELD_SERVICE = "service";
	public static final String FIELD_RESOURCE = "resource";
	
	public static final String TABLE_NAME = Service.TABLE_NAME+Resource.TABLE_NAME;
	
	public static final String COLUMN_SERVICE = Service.TABLE_NAME;
	public static final String COLUMN_RESOURCE = Resource.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_SERVICE_RESOURCE_NAME = COLUMN_SERVICE+COLUMN_RESOURCE;
}
