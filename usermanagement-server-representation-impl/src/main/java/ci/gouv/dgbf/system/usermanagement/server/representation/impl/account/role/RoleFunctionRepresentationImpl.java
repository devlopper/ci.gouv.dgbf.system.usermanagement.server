package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleFunctionDtoCollection;

@Singleton
public class RoleFunctionRepresentationImpl extends AbstractRepresentationEntityImpl<RoleFunction,RoleFunctionBusiness,RoleFunctionDto,RoleFunctionDtoCollection> implements RoleFunctionRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RoleFunction> getPersistenceEntityClass() {
		return RoleFunction.class;
	}
	
}
