package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class PosteLocationTypeDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PosteLocationTypeDto setCode(String code) {
		return (PosteLocationTypeDto) super.setCode(code);
	}
	
	@Override
	public PosteLocationTypeDto setName(String name) {
		return (PosteLocationTypeDto) super.setName(name);
	}
}