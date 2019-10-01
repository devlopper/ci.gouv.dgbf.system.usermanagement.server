package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.hierarchy.AbstractIdentifiedByStringAndCodedAndNamedImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class PrivilegeTypeDto extends AbstractIdentifiedByStringAndCodedAndNamedImpl<PrivilegeTypeDto> implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<PrivilegeTypeDto> parents;
	private ArrayList<PrivilegeTypeDto> children;
	
	@Override
	public PrivilegeTypeDto setCode(String code) {
		return (PrivilegeTypeDto) super.setCode(code);
	}
	
	@Override
	public PrivilegeTypeDto setName(String name) {
		return (PrivilegeTypeDto) super.setName(name);
	}
}
