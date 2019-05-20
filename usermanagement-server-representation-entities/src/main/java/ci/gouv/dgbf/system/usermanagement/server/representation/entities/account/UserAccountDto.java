package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntity;

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
	private RolePosteDtoCollection rolePostes;
	
	@Override
	public UserAccountDto setCode(String code) {
		return (UserAccountDto) super.setCode(code);
	}
	
	public UserDto getUser(Boolean injectIfNull) {
		return (UserDto) __getInjectIfNull__(FIELD_USER, injectIfNull);
	}
	
	public AccountDto getAccount(Boolean injectIfNull) {
		return (AccountDto) __getInjectIfNull__(FIELD_ACCOUNT, injectIfNull);
	}
	
	public UserAccountDto addRolePostes(String...rolePostesCodes) {
		if(rolePostes == null)
			this.rolePostes = new RolePosteDtoCollection();
		for(String index : rolePostesCodes)
			this.rolePostes.add(new RolePosteDto().setCode(index));
		return this;
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	
}
