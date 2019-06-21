package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @Accessors(chain=true)
@Table(name=UserAccountInterimModel.TABLE_NAME)
public class UserAccountInterimModel extends AbstractUserAccountInterimCommon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public UserAccountInterimModel setIdentifier(String identifier) {
		return (UserAccountInterimModel) super.setIdentifier(identifier);
	}
	
	/**/

	public static final String TABLE_NAME = "interimaire";
	
}
