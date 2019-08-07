package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractHierarchyPersistenceImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;

@ApplicationScoped
public class PrivilegeHierarchyPersistenceImpl extends AbstractHierarchyPersistenceImpl<PrivilegeHierarchy,Privilege,PrivilegeHierarchies> implements PrivilegeHierarchyPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
