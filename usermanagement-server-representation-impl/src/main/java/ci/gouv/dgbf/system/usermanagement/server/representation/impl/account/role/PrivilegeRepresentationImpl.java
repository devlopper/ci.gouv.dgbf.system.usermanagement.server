package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.PrivilegeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeDtoCollection;

@ApplicationScoped
public class PrivilegeRepresentationImpl extends AbstractRepresentationEntityImpl<Privilege,PrivilegeBusiness,PrivilegeDto,PrivilegeDtoCollection> implements PrivilegeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<Privilege> getPersistenceEntityClass() {
		return Privilege.class;
	}
	
}
