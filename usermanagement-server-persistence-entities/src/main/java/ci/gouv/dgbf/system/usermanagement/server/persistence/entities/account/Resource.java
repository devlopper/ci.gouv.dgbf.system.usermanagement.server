package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamedAndDescribable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) 
public class Resource extends AbstractIdentifiedByStringAndCodedAndNamedAndDescribable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Resource setIdentifier(String identifier) {
		return (Resource) super.setIdentifier(identifier);
	}
	
	@Override
	public Resource setCode(String code) {
		return (Resource) super.setCode(code);
	}
	
	@Override
	public Resource setName(String name) {
		return (Resource) super.setName(name);
	}
	
	@Override
	public Resource setDescription(String description) {
		return (Resource) super.setDescription(description);
	}
	
	/**/
	
}
