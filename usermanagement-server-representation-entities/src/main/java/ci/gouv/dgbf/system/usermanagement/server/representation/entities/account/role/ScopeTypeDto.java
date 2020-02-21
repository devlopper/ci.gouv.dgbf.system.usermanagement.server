package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringAndCodedAndNamedImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class ScopeTypeDto extends AbstractIdentifiedByStringAndCodedAndNamedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<FunctionDto> functions;
	
	@Override
	public ScopeTypeDto setCode(String code) {
		return (ScopeTypeDto) super.setCode(code);
	}
	
	@Override
	public ScopeTypeDto setName(String name) {
		return (ScopeTypeDto) super.setName(name);
	}
}
