package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDtoCollection;

@ApplicationScoped
public class UserRepresentationImpl extends AbstractRepresentationEntityImpl<User,UserBusiness,UserDto,UserDtoCollection> implements UserRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<User> getPersistenceEntityClass() {
		return User.class;
	}

}
