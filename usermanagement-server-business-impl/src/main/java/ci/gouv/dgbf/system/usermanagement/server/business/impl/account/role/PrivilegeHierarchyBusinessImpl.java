package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.hierarchy.AbstractHierarchyBusinessImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;

@ApplicationScoped
public class PrivilegeHierarchyBusinessImpl extends AbstractHierarchyBusinessImpl<PrivilegeHierarchy, PrivilegeHierarchyPersistence,Privilege,PrivilegeHierarchies> implements PrivilegeHierarchyBusiness,Serializable {
	private static final long serialVersionUID = 1L;
	
}
