package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDtoCollection;

@Singleton
public class RoleFunctionRepresentationImpl extends AbstractRepresentationEntityImpl<RoleFunction,RoleFunctionBusiness,RoleFunctionDto,RoleFunctionDtoCollection> implements RoleFunctionRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RoleFunction> getPersistenceEntityClass() {
		return RoleFunction.class;
	}
	
}
