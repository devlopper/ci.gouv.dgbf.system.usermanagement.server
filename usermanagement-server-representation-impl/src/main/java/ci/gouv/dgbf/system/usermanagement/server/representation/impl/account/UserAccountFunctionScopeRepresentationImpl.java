package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountFunctionScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountFunctionScopeDto;

@ApplicationScoped
public class UserAccountFunctionScopeRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountFunctionScopeDto> implements UserAccountFunctionScopeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
