package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeTypeFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeFunctionDto;

@ApplicationScoped
public class ScopeTypeFunctionRepresentationImpl extends AbstractRepresentationEntityImpl<ScopeTypeFunctionDto> implements ScopeTypeFunctionRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
