package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityLinkedAndNamed;
import org.cyk.utility.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class ScopeDto extends AbstractEntityFromPersistenceEntityLinkedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	private ScopeTypeDto type;
	
	@Override
	public ScopeDto setIdentifier(String identifier) {
		return (ScopeDto) super.setIdentifier(identifier);
	}
	
	@Override
	public String toString() {
		Collection<String> strings = new ArrayList<>();
		if(Boolean.TRUE.equals(__inject__(StringHelper.class).isNotBlank(getName())))
			strings.add("name : "+getName());
		if(type != null)
			strings.add("type : "+type.toString());
		return __inject__(StringHelper.class).concatenate(strings, ",");
	}
}
