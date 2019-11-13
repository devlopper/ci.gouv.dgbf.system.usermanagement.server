package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;

@ApplicationScoped
public class ProfileRepresentationImpl extends AbstractRepresentationEntityImpl<ProfileDto> implements ProfileRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Response exportToKeycloak() {
		__inject__(ProfilePersistence.class).exportToKeycloak();
		return Response.ok().build();
	}
	
}
