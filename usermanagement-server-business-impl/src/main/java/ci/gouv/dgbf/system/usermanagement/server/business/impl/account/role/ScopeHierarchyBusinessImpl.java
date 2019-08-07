package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.hierarchy.AbstractHierarchyBusinessImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchy;

@ApplicationScoped
public class ScopeHierarchyBusinessImpl extends AbstractHierarchyBusinessImpl<ScopeHierarchy, ScopeHierarchyPersistence,Scope,ScopeHierarchies> implements ScopeHierarchyBusiness,Serializable {
	private static final long serialVersionUID = 1L;
	
}
