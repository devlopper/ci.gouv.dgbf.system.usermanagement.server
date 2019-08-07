package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.PrivilegeTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeTypeDtoCollection;

@ApplicationScoped
public class PrivilegeTypeRepresentationImpl extends AbstractRepresentationEntityImpl<PrivilegeType,PrivilegeTypeBusiness,PrivilegeTypeDto,PrivilegeTypeDtoCollection> implements PrivilegeTypeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<PrivilegeType> getPersistenceEntityClass() {
		return PrivilegeType.class;
	}
	
}
