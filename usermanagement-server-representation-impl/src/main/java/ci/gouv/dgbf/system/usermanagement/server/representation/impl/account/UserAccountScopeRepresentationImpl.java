package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountScopeDtoCollection;

@ApplicationScoped
public class UserAccountScopeRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountScope,UserAccountScopeBusiness,UserAccountScopeDto,UserAccountScopeDtoCollection> implements UserAccountScopeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
