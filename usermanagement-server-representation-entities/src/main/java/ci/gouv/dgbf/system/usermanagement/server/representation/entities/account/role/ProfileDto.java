package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntityCodedAndNamed;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class ProfileDto extends AbstractEntityFromPersistenceEntityCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	private ProfileTypeDto type;
	private FunctionDtoCollection functions;
	private PrivilegeDtoCollection privileges;
	
	@Override
	public ProfileDto setCode(String code) {
		return (ProfileDto) super.setCode(code);
	}
	
	@Override
	public ProfileDto setName(String name) {
		return (ProfileDto) super.setName(name);
	}
	
	@XmlElement(name="fonctions")
	public FunctionDtoCollection getFunctions() {
		return functions;
	}
	
	public ProfileDto addFunctionsByCodes(Collection<String> functionsCodes) {
		if(CollectionHelper.isNotEmpty(functionsCodes)) {
			for(String index : functionsCodes)
				if(StringHelper.isNotBlank(index))
					addFunctions(new FunctionDto().setCode(index));
		}
		return this;
	}
	
	public ProfileDto addFunctionsByCodes(String...functionsCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionsCodes))
			addFunctionsByCodes(CollectionHelper.listOf(functionsCodes));
		return this;
	}
	
	public ProfileDto addFunctions(Collection<FunctionDto> functions) {
		if(CollectionHelper.isNotEmpty(functions))
			getFunctions(Boolean.TRUE).add(functions);	
		return this;
	}
	
	public ProfileDto addFunctions(FunctionDto...functions) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functions))
			addFunctions(CollectionHelper.listOf(functions));
		return this;
	}
	
	public FunctionDtoCollection getFunctions(Boolean instanciateIfNull) {
		return (FunctionDtoCollection) __getInstanciateIfNull__(FIELD_FUNCTIONS, instanciateIfNull);
	}
	
	/**/
	
	@XmlElement(name="privileges")
	public PrivilegeDtoCollection getPrivileges() {
		return privileges;
	}
	
	public ProfileDto addPrivilegesByCodes(Collection<String> privilegesCodes) {
		if(CollectionHelper.isNotEmpty(privilegesCodes)) {
			for(String index : privilegesCodes)
				if(StringHelper.isNotBlank(index))
					addPrivileges(new PrivilegeDto().setCode(index));
		}
		return this;
	}
	
	public ProfileDto addPrivilegesByCodes(String...privilegesCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(privilegesCodes))
			addPrivilegesByCodes(CollectionHelper.listOf(privilegesCodes));
		return this;
	}
	
	public ProfileDto addPrivileges(Collection<PrivilegeDto> privileges) {
		if(CollectionHelper.isNotEmpty(privileges))
			getPrivileges(Boolean.TRUE).add(privileges);	
		return this;
	}
	
	public ProfileDto addPrivileges(PrivilegeDto...privileges) {
		if(__inject__(ArrayHelper.class).isNotEmpty(privileges))
			addPrivileges(CollectionHelper.listOf(privileges));
		return this;
	}
	
	public PrivilegeDtoCollection getPrivileges(Boolean instanciateIfNull) {
		return (PrivilegeDtoCollection) __getInstanciateIfNull__(FIELD_PRIVILEGES, instanciateIfNull);
	}
	
	/**/
	
	public static final String FIELD_FUNCTIONS = "functions";
	public static final String FIELD_PRIVILEGES = "privileges";
}
