package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class RolePosteDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private RoleFunctionDto function;
	private RoleFunctionDto ministry;
	private RoleFunctionDto program;
	private RoleFunctionDto administrativeUnit;
	
	@Override
	public RolePosteDto setCode(String code) {
		return (RolePosteDto) super.setCode(code);
	}
	
}
