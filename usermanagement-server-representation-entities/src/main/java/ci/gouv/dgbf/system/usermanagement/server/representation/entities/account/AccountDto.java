package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiedByStringImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class AccountDto extends AbstractIdentifiedByStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private String pass;

	
	@Override
	public AccountDto setIdentifier(String code) {
		return (AccountDto) super.setIdentifier(code);
	}
	
}
