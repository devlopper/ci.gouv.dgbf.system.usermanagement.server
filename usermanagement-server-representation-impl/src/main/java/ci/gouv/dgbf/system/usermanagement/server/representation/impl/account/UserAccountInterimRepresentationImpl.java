package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountInterimRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimDto;

@ApplicationScoped
public class UserAccountInterimRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountInterimDto> implements UserAccountInterimRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
