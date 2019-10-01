package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class FunctionDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FunctionTypeDto type;
	private Boolean isProfileCreatableOnCreate;
	
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
		if(Boolean.TRUE.equals(StringHelper.isNotBlank(getCode())))
			strings.add("code : "+getCode());
		if(Boolean.TRUE.equals(StringHelper.isNotBlank(getName())))
			strings.add("name : "+getName());
		if(type != null)
			strings.add("type : "+type.toString());
		return StringHelper.concatenate(strings, ",");
	}
}
