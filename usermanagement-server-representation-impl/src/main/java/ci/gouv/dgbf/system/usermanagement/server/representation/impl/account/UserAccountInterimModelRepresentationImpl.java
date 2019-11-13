package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountInterimModelRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimModelDto;

@ApplicationScoped
public class UserAccountInterimModelRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountInterimModelDto> implements UserAccountInterimModelRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
