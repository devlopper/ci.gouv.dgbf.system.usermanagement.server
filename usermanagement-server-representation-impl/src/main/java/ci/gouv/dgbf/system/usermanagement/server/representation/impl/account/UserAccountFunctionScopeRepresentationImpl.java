package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountFunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountFunctionScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountFunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountFunctionScopeDtoCollection;

@ApplicationScoped
public class UserAccountFunctionScopeRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountFunctionScope,UserAccountFunctionScopeBusiness,UserAccountFunctionScopeDto,UserAccountFunctionScopeDtoCollection> implements UserAccountFunctionScopeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<UserAccountFunctionScope> getPersistenceEntityClass() {
		return UserAccountFunctionScope.class;
	}
	
}
