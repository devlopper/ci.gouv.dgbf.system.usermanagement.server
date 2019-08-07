package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractPersistenceIdentifiedByStringAndCodedImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;

@ApplicationScoped
public class PrivilegeTypePersistenceImpl extends AbstractPersistenceIdentifiedByStringAndCodedImpl<PrivilegeType,PrivilegeTypeHierarchy,PrivilegeTypeHierarchies,PrivilegeTypeHierarchyPersistence> implements PrivilegeTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
