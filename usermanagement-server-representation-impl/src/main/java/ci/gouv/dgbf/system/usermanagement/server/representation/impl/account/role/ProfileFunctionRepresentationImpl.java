package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileFunctionDtoCollection;

@ApplicationScoped
public class ProfileFunctionRepresentationImpl extends AbstractRepresentationEntityImpl<ProfileFunction,ProfileFunctionBusiness,ProfileFunctionDto,ProfileFunctionDtoCollection> implements ProfileFunctionRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<ProfileFunction> getPersistenceEntityClass() {
		return ProfileFunction.class;
	}
	
}
