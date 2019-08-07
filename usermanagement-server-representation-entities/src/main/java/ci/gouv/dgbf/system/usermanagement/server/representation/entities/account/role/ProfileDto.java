package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;
import org.cyk.utility.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class ProfileDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	private FunctionDtoCollection functions;
	
	@Override
	public ProfileDto setCode(String code) {
		return (ProfileDto) super.setCode(code);
	}
	
	@Override
	public ProfileDto setName(String name) {
		return (ProfileDto) super.setName(name);
	}
	
	public ProfileDto addFunctionsByCodes(Collection<String> functionsCodes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(functionsCodes)) {
			for(String index : functionsCodes)
				if(__inject__(StringHelper.class).isNotBlank(index))
					addFunctions(new FunctionDto().setCode(index));
		}
		return this;
	}
	
	public ProfileDto addFunctionsByCodes(String...functionsCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionsCodes))
			addFunctionsByCodes(__inject__(CollectionHelper.class).instanciate(functionsCodes));
		return this;
	}
	
	public ProfileDto addFunctions(Collection<FunctionDto> functions) {
		if(__inject__(CollectionHelper.class).isNotEmpty(functions))
			getFunctions(Boolean.TRUE).add(functions);	
		return this;
	}
	
	public ProfileDto addFunctions(FunctionDto...functions) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functions))
			addFunctions(__inject__(CollectionHelper.class).instanciate(functions));
		return this;
	}
	
	public FunctionDtoCollection getFunctions(Boolean instanciateIfNull) {
		return (FunctionDtoCollection) __getInstanciateIfNull__(FIELD_FUNCTIONS, instanciateIfNull);
	}
	
	/**/
	
	public static final String FIELD_FUNCTIONS = "functions";
}
