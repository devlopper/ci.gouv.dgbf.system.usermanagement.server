package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public abstract class AbstractUserAccountInterimDto extends AbstractEntityFromPersistenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserAccountDto userAccount;
	private UserAccountDto interim;
	
	public UserAccountDto getUserAccount(Boolean injectIfNull) {
		return (UserAccountDto) __getInjectIfNull__(FIELD_USER_ACCOUNT, injectIfNull);
	}
	
	public UserAccountDto getInterim(Boolean injectIfNull) {
		return (UserAccountDto) __getInjectIfNull__(FIELD_INTERIM, injectIfNull);
	}
	
	/**/
	
	public static final String FIELD_USER_ACCOUNT = "userAccount";
	public static final String FIELD_INTERIM = "interim";
	
}
