package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;

@ApplicationScoped
public class FunctionScopeRepresentationImpl extends AbstractRepresentationEntityImpl<FunctionScopeDto> implements FunctionScopeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
