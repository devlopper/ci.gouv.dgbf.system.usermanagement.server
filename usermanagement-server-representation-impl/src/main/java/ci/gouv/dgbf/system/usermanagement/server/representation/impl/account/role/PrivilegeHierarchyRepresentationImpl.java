package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.PrivilegeHierarchyRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeHierarchyDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeHierarchyDtoCollection;

@ApplicationScoped
public class PrivilegeHierarchyRepresentationImpl extends AbstractRepresentationEntityImpl<PrivilegeHierarchy,PrivilegeHierarchyBusiness,PrivilegeHierarchyDto,PrivilegeHierarchyDtoCollection> implements PrivilegeHierarchyRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<PrivilegeHierarchy> getPersistenceEntityClass() {
		return PrivilegeHierarchy.class;
	}
	
}
