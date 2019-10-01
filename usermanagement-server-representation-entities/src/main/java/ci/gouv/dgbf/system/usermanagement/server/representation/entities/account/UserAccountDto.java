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
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollection;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollection;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDtoCollection;
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
	private ScopeDtoCollection scopes;
	private FunctionScopeDtoCollection functionScopes;
	
	public UserDto getUser(Boolean injectIfNull) {
		return (UserDto) __getInjectIfNull__(FIELD_USER, injectIfNull);
	}
	
	public AccountDto getAccount(Boolean injectIfNull) {
		return (AccountDto) __getInjectIfNull__(FIELD_ACCOUNT, injectIfNull);
	}
	
	public UserAccountDto addFunctionsByCodes(Collection<String> functionsCodes) {
		if(CollectionHelper.isNotEmpty(functionsCodes)) {
			for(String index : functionsCodes)
				if(StringHelper.isNotBlank(index))
					addFunctions(new FunctionDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addFunctionsByCodes(String...functionsCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionsCodes))
			addFunctionsByCodes(CollectionHelper.listOf(functionsCodes));
		return this;
	}
	
	public UserAccountDto addFunctions(Collection<FunctionDto> functions) {
		if(CollectionHelper.isNotEmpty(functions))
			getFunctions(Boolean.TRUE).add(functions);	
		return this;
	}
	
	public UserAccountDto addFunctions(FunctionDto...functions) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functions))
			addFunctions(CollectionHelper.listOf(functions));
		return this;
	}
	
	public FunctionDtoCollection getFunctions(Boolean instanciateIfNull) {
		return (FunctionDtoCollection) __getInstanciateIfNull__(FIELD_FUNCTIONS, instanciateIfNull);
	}
	
	public UserAccountDto addFunctionScopesByCodes(Collection<String> functionScopesCodes) {
		if(CollectionHelper.isNotEmpty(functionScopesCodes)) {
			for(String index : functionScopesCodes)
				if(StringHelper.isNotBlank(index))
					addFunctionScopes(new FunctionScopeDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addFunctionScopesByCodes(String...functionScopesCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionScopesCodes))
			addFunctionScopesByCodes(CollectionHelper.listOf(functionScopesCodes));
		return this;
	}
	
	public UserAccountDto addFunctionScopes(Collection<FunctionScopeDto> functionScopes) {
		if(CollectionHelper.isNotEmpty(functionScopes))
			getFunctionScopes(Boolean.TRUE).add(functionScopes);	
		return this;
	}
	
	public UserAccountDto addFunctionScopes(FunctionScopeDto...functionScopes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functionScopes))
			addFunctionScopes(CollectionHelper.listOf(functionScopes));
		return this;
	}
	
	public FunctionScopeDtoCollection getFunctionScopes(Boolean instanciateIfNull) {
		return (FunctionScopeDtoCollection) __getInstanciateIfNull__(FIELD_FUNCTION_SCOPES, instanciateIfNull);
	}
	
	/**/
	
	public UserAccountDto addProfilesByCodes(Collection<String> profilesCodes) {
		if(CollectionHelper.isNotEmpty(profilesCodes)) {
			for(String index : profilesCodes)
				if(StringHelper.isNotBlank(index))
					addProfiles(new ProfileDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addProfilesByCodes(String...profilesCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(profilesCodes))
			addProfilesByCodes(CollectionHelper.listOf(profilesCodes));
		return this;
	}
	
	public UserAccountDto addProfiles(Collection<ProfileDto> profiles) {
		if(CollectionHelper.isNotEmpty(profiles))
			getProfiles(Boolean.TRUE).add(profiles);	
		return this;
	}
	
	public UserAccountDto addProfiles(ProfileDto...profiles) {
		if(__inject__(ArrayHelper.class).isNotEmpty(profiles))
			addProfiles(CollectionHelper.listOf(profiles));
		return this;
	}
	
	public ProfileDtoCollection getProfiles(Boolean instanciateIfNull) {
		return (ProfileDtoCollection) __getInstanciateIfNull__(FIELD_PROFILES, instanciateIfNull);
	}
	
	public UserAccountDto addScopesByIdentifiers(Collection<String> identifiers) {
		if(CollectionHelper.isNotEmpty(identifiers)) {
			for(String index : identifiers)
				if(StringHelper.isNotBlank(index))
					addScopes(new ScopeDto().setIdentifier(index));
		}
		return this;
	}
	
	public UserAccountDto addScopesByIdentifiers(String...identifiers) {
		if(__inject__(ArrayHelper.class).isNotEmpty(identifiers))
			addScopesByIdentifiers(CollectionHelper.listOf(identifiers));
		return this;
	}
	
	public UserAccountDto addScopes(Collection<ScopeDto> scopes) {
		if(CollectionHelper.isNotEmpty(scopes))
			getScopes(Boolean.TRUE).add(scopes);	
		return this;
	}
	
	public UserAccountDto addScopes(ScopeDto...scopes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(scopes))
			addScopes(CollectionHelper.listOf(scopes));
		return this;
	}
	
	public ScopeDtoCollection getScopes(Boolean instanciateIfNull) {
		return (ScopeDtoCollection) __getInstanciateIfNull__(FIELD_SCOPES, instanciateIfNull);
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_FUNCTIONS = "functions";
	public static final String FIELD_PROFILES = "profiles";
	public static final String FIELD_FUNCTION_SCOPES = "functionScopes";
	public static final String FIELD_SCOPES = "scopes";
	
}
