package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityLinkedAndNamed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class PosteLocationDto extends AbstractEntityFromPersistenceEntityLinkedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	private PosteLocationTypeDto type;
	
	@Override
	public PosteLocationDto setIdentifier(String identifier) {
		return (PosteLocationDto) super.setIdentifier(identifier);
	}
	
}