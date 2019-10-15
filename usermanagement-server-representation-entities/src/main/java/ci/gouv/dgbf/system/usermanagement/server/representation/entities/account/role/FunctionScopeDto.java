package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringAndCodedAndNamedImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class FunctionScopeDto extends AbstractIdentifiedByStringAndCodedAndNamedImpl implements Serializable {
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
	
	@Override
	public String toString() {
		Collection<String> strings = new ArrayList<>();
		if(function != null)
			strings.add("Function("+function.toString()+")");
		if(scope != null)
			strings.add("Scope("+scope.toString()+")");
		return StringHelper.concatenate(strings, ",");
	}
}
