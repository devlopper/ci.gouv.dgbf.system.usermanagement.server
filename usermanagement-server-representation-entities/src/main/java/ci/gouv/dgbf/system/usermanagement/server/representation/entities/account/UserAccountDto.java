package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FieldContainerFunctionScopes;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FieldContainerFunctions;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FieldContainerPrivileges;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FieldContainerProfiles;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FieldContainerScopes;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserAccountDto extends AbstractIdentifiedByStringImpl implements FieldContainerFunctions,FieldContainerProfiles,FieldContainerScopes,FieldContainerFunctionScopes,FieldContainerPrivileges,Serializable {
	private static final long serialVersionUID = 1L;

	private UserDto user;
	private AccountDto account;
	private Byte notation;
	private String color;
	private ArrayList<FunctionDto> functions;
	private ArrayList<ProfileDto> profiles;
	private ArrayList<PrivilegeDto> privileges;
	private ArrayList<ScopeDto> scopes;
	private ArrayList<FunctionScopeDto> functionScopes;
	
	public UserDto getUser(Boolean injectIfNull) {
		if(user == null && Boolean.TRUE.equals(injectIfNull))
			user = new UserDto();
		return user;
	}
	
	public AccountDto getAccount(Boolean injectIfNull) {
		if(account == null && Boolean.TRUE.equals(injectIfNull))
			account = new AccountDto();
		return account;
	}
	
	@Override
	public UserAccountDto addFunctions(FunctionDto... functions) {
		return (UserAccountDto) FieldContainerFunctions.super.addFunctions(functions);
	}
	
	@Override
	public UserAccountDto addFunctionsByCodes(String... codes) {
		return (UserAccountDto) FieldContainerFunctions.super.addFunctionsByCodes(codes);
	}
	
	@Override
	public UserAccountDto addFunctionScopes(
			FunctionScopeDto... functionScopes) {
		return (UserAccountDto) FieldContainerFunctionScopes.super.addFunctionScopes(functionScopes);
	}
	
	@Override
	public UserAccountDto addFunctionScopesByCodes(String... codes) {
		return (UserAccountDto) FieldContainerFunctionScopes.super.addFunctionScopesByCodes(codes);
	}
	
	@Override
	public UserAccountDto addProfiles(ProfileDto... profiles) {
		return (UserAccountDto) FieldContainerProfiles.super.addProfiles(profiles);
	}
	
	@Override
	public UserAccountDto addProfilesByCodes(String... codes) {
		return (UserAccountDto) FieldContainerProfiles.super.addProfilesByCodes(codes);
	}
	
	@Override
	public UserAccountDto addScopes(ScopeDto... scopes) {
		return (UserAccountDto) FieldContainerScopes.super.addScopes(scopes);
	}
	
	@Override
	public UserAccountDto addScopesByCodes(String... codes) {
		return (UserAccountDto) FieldContainerScopes.super.addScopesByCodes(codes);
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	
}
