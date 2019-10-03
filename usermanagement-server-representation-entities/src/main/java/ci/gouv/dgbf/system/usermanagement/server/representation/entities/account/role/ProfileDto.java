package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class ProfileDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements FieldContainerFunctions,FieldContainerPrivileges, Serializable {
	private static final long serialVersionUID = 1L;

	private ProfileTypeDto type;
	private Collection<FunctionDto> functions;
	private Collection<PrivilegeDto> privileges;
	
	@Override
	public ProfileDto setCode(String code) {
		return (ProfileDto) super.setCode(code);
	}
	
	@Override
	public ProfileDto setName(String name) {
		return (ProfileDto) super.setName(name);
	}
	
	@Override
	public ProfileDto addFunctionsByCodes(String... codes) {
		return (ProfileDto) FieldContainerFunctions.super.addFunctionsByCodes(codes);
	}
	
	@Override
	public ProfileDto addPrivilegesByCodes(String... codes) {
		return (ProfileDto) FieldContainerPrivileges.super.addPrivilegesByCodes(codes);
	}
}
