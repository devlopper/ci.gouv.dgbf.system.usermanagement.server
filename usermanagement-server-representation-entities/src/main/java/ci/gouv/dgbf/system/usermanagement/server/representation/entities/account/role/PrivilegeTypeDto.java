package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class PrivilegeTypeDto extends org.cyk.utility.server.representation.hierarchy.AbstractNodeCodedAndNamed<PrivilegeTypeDto,PrivilegeTypeDtoCollection> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PrivilegeTypeDto setCode(String code) {
		return (PrivilegeTypeDto) super.setCode(code);
	}
	
	@Override
	public PrivilegeTypeDto setName(String name) {
		return (PrivilegeTypeDto) super.setName(name);
	}
}
