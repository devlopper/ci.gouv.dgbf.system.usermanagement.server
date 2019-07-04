package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @Accessors(chain=true)
@Table(name=Service.TABLE_NAME)
public class Service extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = COLUMN_URL) @NotNull private String url;
	
	@Override
	public Service setIdentifier(String identifier) {
		return (Service) super.setIdentifier(identifier);
	}
	
	/**/

	public static final String FIELD_URL = "url";
	
	public static final String TABLE_NAME = "svc";
	
	public static final String COLUMN_URL = "url";
	
}
