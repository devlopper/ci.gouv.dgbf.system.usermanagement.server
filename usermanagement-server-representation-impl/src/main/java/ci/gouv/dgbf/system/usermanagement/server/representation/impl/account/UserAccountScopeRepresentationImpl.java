package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountScopeDto;

@ApplicationScoped
public class UserAccountScopeRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountScopeDto> implements UserAccountScopeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
