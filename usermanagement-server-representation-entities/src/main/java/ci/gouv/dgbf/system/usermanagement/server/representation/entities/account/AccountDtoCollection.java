package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.cyk.utility.server.representation.AbstractEntityCollection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@XmlSeeAlso(AccountDto.class)
public class AccountDtoCollection extends AbstractEntityCollection<AccountDto> implements Serializable {
	private static final long serialVersionUID = 1L;

	public AccountDtoCollection add(String code,String pass) {
		add(new AccountDto().setCode(code).setPass(pass));
		return this;
	}
	
	public AccountDtoCollection add(String code) {
		return add(code, null);
	}
		
}
