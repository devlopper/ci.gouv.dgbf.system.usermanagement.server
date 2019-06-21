package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserAccountInterimModelDto extends AbstractUserAccountInterimDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public UserAccountDto getUserAccount(Boolean injectIfNull) {
		return super.getUserAccount(injectIfNull);
	}
	
	@Override
	public UserAccountDto getInterim(Boolean injectIfNull) {
		return super.getInterim(injectIfNull);
	}
	
}
