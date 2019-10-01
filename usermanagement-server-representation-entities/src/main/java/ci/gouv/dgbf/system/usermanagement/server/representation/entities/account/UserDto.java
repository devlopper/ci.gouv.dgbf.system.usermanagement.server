package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntity;
import org.cyk.utility.__kernel__.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserDto extends AbstractEntityFromPersistenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastNames;
	private String names;
	private String electronicMailAddress;
	private FunctionDtoCollection functions;
	
	public UserDto addFunctionsByCodes(Collection<String> functionsCodes) {
		if(CollectionHelper.isNotEmpty(functionsCodes)) {
			for(String index : functionsCodes)
				if(StringHelper.isNotBlank(index))
					addFunctions(new FunctionDto().setCode(index));
		}
		return this;
	}
	
	public UserDto addFunctionsByCodes(String...functionsCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionsCodes))
			addFunctionsByCodes(CollectionHelper.listOf(functionsCodes));
		return this;
	}
	
	public UserDto addFunctions(Collection<FunctionDto> functions) {
		if(CollectionHelper.isNotEmpty(functions))
			getFunctions(Boolean.TRUE).add(functions);	
		return this;
	}
	
	public UserDto addFunctions(FunctionDto...functions) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functions))
			addFunctions(CollectionHelper.listOf(functions));
		return this;
	}
	
	public FunctionDtoCollection getFunctions(Boolean instanciateIfNull) {
		return (FunctionDtoCollection) __getInstanciateIfNull__(FIELD_FUNCTIONS, instanciateIfNull);
	}
	
	public static final String FIELD_FUNCTIONS = "functions";
	
}
