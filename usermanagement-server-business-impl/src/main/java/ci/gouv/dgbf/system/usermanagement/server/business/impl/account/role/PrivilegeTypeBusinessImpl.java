package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.hierarchy.AbstractBusinessIdentifiedByStringAndCodedImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeTypeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;

@ApplicationScoped
public class PrivilegeTypeBusinessImpl extends AbstractBusinessIdentifiedByStringAndCodedImpl<PrivilegeType, PrivilegeTypePersistence,PrivilegeTypeHierarchy,PrivilegeTypeHierarchies,PrivilegeTypeHierarchyPersistence,PrivilegeTypeHierarchyBusiness> implements PrivilegeTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;
	
}
