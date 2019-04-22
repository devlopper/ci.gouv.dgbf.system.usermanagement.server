package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamedAndDescribable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) 
public class Action extends AbstractIdentifiedByStringAndCodedAndNamedAndDescribable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Action setIdentifier(String identifier) {
		return (Action) super.setIdentifier(identifier);
	}
	
	@Override
	public Action setCode(String code) {
		return (Action) super.setCode(code);
	}
	
	@Override
	public Action setName(String name) {
		return (Action) super.setName(name);
	}
	
	@Override
	public Action setDescription(String description) {
		return (Action) super.setDescription(description);
	}
	
	/**/
	
}
