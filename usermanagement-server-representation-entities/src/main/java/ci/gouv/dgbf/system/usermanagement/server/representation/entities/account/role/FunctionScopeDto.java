package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class FunctionScopeDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FunctionDto function;
	private ScopeDto scope;
	
	@Override
	public FunctionScopeDto setCode(String code) {
		return (FunctionScopeDto) super.setCode(code);
	}
	
	@Override
	public FunctionScopeDto setName(String name) {
		return (FunctionScopeDto) super.setName(name);
	}
	
}
