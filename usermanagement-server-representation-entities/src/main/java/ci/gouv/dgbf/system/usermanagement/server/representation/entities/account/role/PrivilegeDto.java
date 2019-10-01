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
public class PrivilegeDto extends AbstractIdentifiedByStringAndCodedAndNamedImpl<PrivilegeDto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<PrivilegeDto> parents;
	private ArrayList<PrivilegeDto> children;
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
