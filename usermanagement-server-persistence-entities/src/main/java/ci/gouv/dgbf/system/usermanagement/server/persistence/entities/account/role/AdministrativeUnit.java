package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndLinkedByStringAndNamed;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @Accessors(chain=true)
@Table(name=AdministrativeUnit.TABLE_NAME)
public class AdministrativeUnit extends AbstractIdentifiedByStringAndLinkedByStringAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public AdministrativeUnit setIdentifier(String identifier) {
		return (AdministrativeUnit) super.setIdentifier(identifier);
	}
	
	@Override @JsonProperty(value="uuid")
	public String getLink() {
		return super.getLink();
	}
	
	@Override @JsonProperty(value="libelleLong")
	public String getName() {
		return super.getName();
	}
	
	/**/

	public static final String TABLE_NAME = "unite_administrative";
	
}
