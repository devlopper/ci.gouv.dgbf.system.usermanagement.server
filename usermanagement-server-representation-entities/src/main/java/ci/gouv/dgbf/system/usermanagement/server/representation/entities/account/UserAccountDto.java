package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntity;
import org.cyk.utility.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollection;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollection;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserAccountDto extends AbstractEntityFromPersistenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserDto user;
	private AccountDto account;
	private FunctionDtoCollection functions;
	private ProfileDtoCollection profiles;
	private FunctionScopeDtoCollection functionScopes;
	
	public UserDto getUser(Boolean injectIfNull) {
		return (UserDto) __getInjectIfNull__(FIELD_USER, injectIfNull);
	}
	
	public AccountDto getAccount(Boolean injectIfNull) {
		return (AccountDto) __getInjectIfNull__(FIELD_ACCOUNT, injectIfNull);
	}
	
	public UserAccountDto addFunctionsByCodes(Collection<String> functionsCodes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(functionsCodes)) {
			for(String index : functionsCodes)
				if(__inject__(StringHelper.class).isNotBlank(index))
					addFunctions(new FunctionDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addFunctionsByCodes(String...functionsCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionsCodes))
			addFunctionsByCodes(__inject__(CollectionHelper.class).instanciate(functionsCodes));
		return this;
	}
	
	public UserAccountDto addFunctions(Collection<FunctionDto> functions) {
		if(__inject__(CollectionHelper.class).isNotEmpty(functions))
			getFunctions(Boolean.TRUE).add(functions);	
		return this;
	}
	
	public UserAccountDto addFunctions(FunctionDto...functions) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functions))
			addFunctions(__inject__(CollectionHelper.class).instanciate(functions));
		return this;
	}
	
	public FunctionDtoCollection getFunctions(Boolean instanciateIfNull) {
		return (FunctionDtoCollection) __getInstanciateIfNull__(FIELD_FUNCTIONS, instanciateIfNull);
	}
	
	public UserAccountDto addFunctionScopesByCodes(Collection<String> functionScopesCodes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(functionScopesCodes)) {
			for(String index : functionScopesCodes)
				if(__inject__(StringHelper.class).isNotBlank(index))
					addFunctionScopes(new FunctionScopeDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addFunctionScopesByCodes(String...functionScopesCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionScopesCodes))
			addFunctionScopesByCodes(__inject__(CollectionHelper.class).instanciate(functionScopesCodes));
		return this;
	}
	
	public UserAccountDto addFunctionScopes(Collection<FunctionScopeDto> functionScopes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(functionScopes))
			getFunctionScopes(Boolean.TRUE).add(functionScopes);	
		return this;
	}
	
	public UserAccountDto addFunctionScopes(FunctionScopeDto...functionScopes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionScopes))
			addFunctionScopes(__inject__(CollectionHelper.class).instanciate(functionScopes));
		return this;
	}
	
	public FunctionScopeDtoCollection getFunctionScopes(Boolean instanciateIfNull) {
		return (FunctionScopeDtoCollection) __getInstanciateIfNull__(FIELD_FUNCTION_SCOPES, instanciateIfNull);
	}
	
	/**/
	
	public UserAccountDto addProfilesByCodes(Collection<String> profilesCodes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(profilesCodes)) {
			for(String index : profilesCodes)
				if(__inject__(StringHelper.class).isNotBlank(index))
					addProfiles(new ProfileDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addProfilesByCodes(String...profilesCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(profilesCodes))
			addProfilesByCodes(__inject__(CollectionHelper.class).instanciate(profilesCodes));
		return this;
	}
	
	public UserAccountDto addProfiles(Collection<ProfileDto> profiles) {
		if(__inject__(CollectionHelper.class).isNotEmpty(profiles))
			getProfiles(Boolean.TRUE).add(profiles);	
		return this;
	}
	
	public UserAccountDto addProfiles(ProfileDto...profiles) {
		if(__inject__(ArrayHelper.class).isNotEmpty(profiles))
			addProfiles(__inject__(CollectionHelper.class).instanciate(profiles));
		return this;
	}
	
	public ProfileDtoCollection getProfiles(Boolean instanciateIfNull) {
		return (ProfileDtoCollection) __getInstanciateIfNull__(FIELD_PROFILES, instanciateIfNull);
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_FUNCTIONS = "functions";
	public static final String FIELD_PROFILES = "profiles";
	public static final String FIELD_FUNCTION_SCOPES = "functionScopes";
	
}
