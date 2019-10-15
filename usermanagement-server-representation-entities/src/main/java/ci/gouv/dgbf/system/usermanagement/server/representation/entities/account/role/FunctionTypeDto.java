package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringAndCodedAndNamedImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class FunctionTypeDto extends AbstractIdentifiedByStringAndCodedAndNamedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public FunctionTypeDto setCode(String code) {
		return (FunctionTypeDto) super.setCode(code);
	}
	
	@Override
	public FunctionTypeDto setName(String name) {
		return (FunctionTypeDto) super.setName(name);
	}
}
