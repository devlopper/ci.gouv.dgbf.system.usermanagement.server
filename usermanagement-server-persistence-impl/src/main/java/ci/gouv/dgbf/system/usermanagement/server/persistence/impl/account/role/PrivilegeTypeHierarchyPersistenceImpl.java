package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractHierarchyPersistenceImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;

@ApplicationScoped
public class PrivilegeTypeHierarchyPersistenceImpl extends AbstractHierarchyPersistenceImpl<PrivilegeTypeHierarchy,PrivilegeType,PrivilegeTypeHierarchies> implements PrivilegeTypeHierarchyPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
