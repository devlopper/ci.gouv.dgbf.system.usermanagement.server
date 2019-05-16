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
public class AccountDto extends AbstractEntityFromPersistenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String pass;

	
	@Override
	public AccountDto setIdentifier(String code) {
		return (AccountDto) super.setIdentifier(code);
	}
	
}
