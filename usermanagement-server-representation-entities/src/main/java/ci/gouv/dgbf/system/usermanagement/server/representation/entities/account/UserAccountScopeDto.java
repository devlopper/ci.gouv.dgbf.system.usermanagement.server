package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserAccountScopeDto extends AbstractIdentifiedByStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserAccountDto userAccount;
	private ScopeDto scope;
	
}
