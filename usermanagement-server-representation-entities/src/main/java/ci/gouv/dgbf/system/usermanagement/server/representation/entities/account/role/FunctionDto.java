package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;
import org.cyk.utility.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class FunctionDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FunctionCategoryDto category;
	
	@Override
	public FunctionDto setCode(String code) {
		return (FunctionDto) super.setCode(code);
	}
	
	@Override
	public FunctionDto setName(String name) {
		return (FunctionDto) super.setName(name);
	}
	
	@Override
	public String toString() {
		Collection<String> strings = new ArrayList<>();
		if(Boolean.TRUE.equals(__inject__(StringHelper.class).isNotBlank(getCode())))
			strings.add("code : "+getCode());
		if(Boolean.TRUE.equals(__inject__(StringHelper.class).isNotBlank(getName())))
			strings.add("name : "+getName());
		if(category != null)
			strings.add("category : "+category.toString());
		return __inject__(StringHelper.class).concatenate(strings, ",");
	}
}
