package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.hierarchy.AbstractHierarchyBusinessImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeTypeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;

@ApplicationScoped
public class PrivilegeTypeHierarchyBusinessImpl extends AbstractHierarchyBusinessImpl<PrivilegeTypeHierarchy, PrivilegeTypeHierarchyPersistence,PrivilegeType,PrivilegeTypeHierarchies> implements PrivilegeTypeHierarchyBusiness,Serializable {
	private static final long serialVersionUID = 1L;
	
}
