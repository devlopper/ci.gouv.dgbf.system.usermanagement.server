package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) 
public class ResourceAction extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull private Resource resource;
	@NotNull private Action action;
	
	@Override
	public ResourceAction setIdentifier(String identifier) {
		return (ResourceAction) super.setIdentifier(identifier);
	}
	
	/**/
	
	public static final String FIELD_RESOURCE = "resource";
	public static final String FIELD_ACTION = "action";
}
