package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserAccountProfileDto extends AbstractEntityFromPersistenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserAccountDto userAccount;
	private ProfileDto profile;
	
}
