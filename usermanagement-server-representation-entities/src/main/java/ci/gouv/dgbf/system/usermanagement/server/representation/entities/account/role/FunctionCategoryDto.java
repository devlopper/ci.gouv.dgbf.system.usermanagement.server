package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class FunctionCategoryDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public FunctionCategoryDto setCode(String code) {
		return (FunctionCategoryDto) super.setCode(code);
	}
	
	@Override
	public FunctionCategoryDto setName(String name) {
		return (FunctionCategoryDto) super.setName(name);
	}
}
