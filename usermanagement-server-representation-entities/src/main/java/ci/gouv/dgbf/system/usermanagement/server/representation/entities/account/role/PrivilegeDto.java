package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class PrivilegeDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private PrivilegeTypeDto type;
	
	@Override
	public PrivilegeDto setCode(String code) {
		return (PrivilegeDto) super.setCode(code);
	}
	
	@Override
	public PrivilegeDto setName(String name) {
		return (PrivilegeDto) super.setName(name);
	}
	
}
