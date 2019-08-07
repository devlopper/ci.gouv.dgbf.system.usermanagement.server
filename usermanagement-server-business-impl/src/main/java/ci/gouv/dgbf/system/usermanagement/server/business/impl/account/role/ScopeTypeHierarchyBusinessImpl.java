package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.hierarchy.AbstractHierarchyBusinessImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;

@ApplicationScoped
public class ScopeTypeHierarchyBusinessImpl extends AbstractHierarchyBusinessImpl<ScopeTypeHierarchy, ScopeTypeHierarchyPersistence,ScopeType,ScopeTypeHierarchies> implements ScopeTypeHierarchyBusiness,Serializable {
	private static final long serialVersionUID = 1L;
	
}
