package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringAndCodedAndNamedImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class ProfileTypeDto extends AbstractIdentifiedByStringAndCodedAndNamedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public ProfileTypeDto setCode(String code) {
		return (ProfileTypeDto) super.setCode(code);
	}
	
	@Override
	public ProfileTypeDto setName(String name) {
		return (ProfileTypeDto) super.setName(name);
	}
}
