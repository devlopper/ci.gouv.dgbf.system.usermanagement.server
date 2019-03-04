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
@XmlSeeAlso(UserNaturalPersonDto.class)
public class UserNaturalPersonDtoCollection extends AbstractEntityCollection<UserNaturalPersonDto> implements Serializable {
	private static final long serialVersionUID = 1L;

	public UserNaturalPersonDtoCollection add(String firstName,String lastNames) {
		add(new UserNaturalPersonDto().setFirstName(firstName).setLastNames(lastNames));
		return this;
	}
	
}
