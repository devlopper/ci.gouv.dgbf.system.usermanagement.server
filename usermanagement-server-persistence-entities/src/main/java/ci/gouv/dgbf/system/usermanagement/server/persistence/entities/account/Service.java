package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) 
public class Service extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Service setIdentifier(String identifier) {
		return (Service) super.setIdentifier(identifier);
	}
	
	/**/
	
}
