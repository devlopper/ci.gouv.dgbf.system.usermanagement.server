package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollection;

@ApplicationScoped
public class FunctionScopeRepresentationImpl extends AbstractRepresentationEntityImpl<FunctionScope,FunctionScopeBusiness,FunctionScopeDto,FunctionScopeDtoCollection> implements FunctionScopeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
