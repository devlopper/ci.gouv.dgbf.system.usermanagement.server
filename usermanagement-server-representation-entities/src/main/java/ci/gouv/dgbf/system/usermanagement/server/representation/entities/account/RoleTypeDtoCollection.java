package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.cyk.utility.server.representation.AbstractEntityCollection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@XmlSeeAlso(RoleTypeDto.class)
public class RoleTypeDtoCollection extends AbstractEntityCollection<RoleTypeDto> implements Serializable {
	private static final long serialVersionUID = 1L;

	public RoleTypeDtoCollection add(String code,String name) {
		add(new RoleTypeDto().setCode(code).setName(name));
		return this;
	}
	
	public RoleTypeDtoCollection add(String code) {
		return add(code, null);
	}
		
}
