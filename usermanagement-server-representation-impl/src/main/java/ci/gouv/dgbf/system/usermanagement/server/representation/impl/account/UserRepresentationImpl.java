package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;

@ApplicationScoped
public class UserRepresentationImpl extends AbstractRepresentationEntityImpl<UserDto> implements UserRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
