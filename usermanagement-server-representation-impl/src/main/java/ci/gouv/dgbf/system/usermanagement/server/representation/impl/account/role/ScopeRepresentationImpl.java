package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;

@ApplicationScoped
public class ScopeRepresentationImpl extends AbstractRepresentationEntityImpl<ScopeDto> implements ScopeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Response import_(List<String> uniformResourceIdentifiers, Boolean ignoreKnownUniformResourceIdentifiers) {
		__inject__(ScopeBusiness.class).import_(null);
		return Response.ok("DONE").build();
	}
	
}
