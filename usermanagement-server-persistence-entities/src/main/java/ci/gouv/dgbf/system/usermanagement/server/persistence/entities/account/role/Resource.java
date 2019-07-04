package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Access(AccessType.FIELD) @Table(name=Resource.TABLE_NAME)
public class Resource extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = COLUMN_URL) @NotNull private String url;
	
	@Override
	public Resource setIdentifier(String identifier) {
		return (Resource) super.setIdentifier(identifier);
	}
	
	@Override
	public Resource setCode(String code) {
		return (Resource) super.setCode(code);
	}
	
	@Override
	public Resource setName(String name) {
		return (Resource) super.setName(name);
	}
	
	/**/
	
	public static final String FIELD_URL = "url";
	
	public static final String TABLE_NAME = "rsc";
	
	public static final String COLUMN_URL = "url";
}
