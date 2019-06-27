package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileRoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileRoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileRoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileRoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileRoleFunctionDtoCollection;

@ApplicationScoped
public class ProfileRoleFunctionRepresentationImpl extends AbstractRepresentationEntityImpl<ProfileRoleFunction,ProfileRoleFunctionBusiness,ProfileRoleFunctionDto,ProfileRoleFunctionDtoCollection> implements ProfileRoleFunctionRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<ProfileRoleFunction> getPersistenceEntityClass() {
		return ProfileRoleFunction.class;
	}
	
}
