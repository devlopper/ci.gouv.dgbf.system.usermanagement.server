package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public abstract class AbstractUserAccountInterimDto extends AbstractIdentifiedByStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserAccountDto userAccount;
	private UserAccountDto interim;
	
	public UserAccountDto getUserAccount(Boolean injectIfNull) {
		if(userAccount == null && Boolean.TRUE.equals(injectIfNull))
			userAccount = new UserAccountDto();
		return userAccount;
	}
	
	public UserAccountDto getInterim(Boolean injectIfNull) {
		if(interim == null && Boolean.TRUE.equals(injectIfNull))
			interim = new UserAccountDto();
		return interim;
	}
	
	/**/
	
	public static final String FIELD_USER_ACCOUNT = "userAccount";
	public static final String FIELD_INTERIM = "interim";
	
}
