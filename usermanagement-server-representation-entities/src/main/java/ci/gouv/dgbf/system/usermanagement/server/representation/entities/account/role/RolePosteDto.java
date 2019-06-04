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
	private MinistryDto ministry;
	private ProgramDto program;
	private AdministrativeUnitDto administrativeUnit;
	
	@Override
	public RolePosteDto setCode(String code) {
		return (RolePosteDto) super.setCode(code);
	}
	
	@Override
	public RolePosteDto setName(String name) {
		return (RolePosteDto) super.setName(name);
	}
	
}
