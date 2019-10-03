package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FieldContainerFunctions;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserDto extends AbstractIdentifiedByStringImpl implements FieldContainerFunctions, Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastNames;
	private String names;
	private String electronicMailAddress;
	private Collection<FunctionDto> functions;
	
	@Override
	public UserDto addFunctionsByCodes(String... codes) {
		return (UserDto) FieldContainerFunctions.super.addFunctionsByCodes(codes);
	}
	
}
