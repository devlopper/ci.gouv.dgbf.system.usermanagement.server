package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamedAndDescribable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) 
public class Service extends AbstractIdentifiedByStringAndCodedAndNamedAndDescribable implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uniformResourceLocator;
	
	@Override
	public Service setIdentifier(String identifier) {
		return (Service) super.setIdentifier(identifier);
	}
	
	@Override
	public Service setCode(String code) {
		return (Service) super.setCode(code);
	}
	
	@Override
	public Service setName(String name) {
		return (Service) super.setName(name);
	}
	
	@Override
	public Service setDescription(String description) {
		return (Service) super.setDescription(description);
	}
	
	/**/
	
}
