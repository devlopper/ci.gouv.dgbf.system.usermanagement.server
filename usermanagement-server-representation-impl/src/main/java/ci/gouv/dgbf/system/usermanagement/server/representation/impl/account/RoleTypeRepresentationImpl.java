package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleTypeDtoCollection;

@Singleton
public class RoleTypeRepresentationImpl extends AbstractRepresentationEntityImpl<RoleType,RoleTypeBusiness,RoleTypeDto,RoleTypeDtoCollection> implements RoleTypeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RoleType> getPersistenceEntityClass() {
		return RoleType.class;
	}
	
}
