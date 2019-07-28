package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.hierarchy.AbstractHierarchyPersistenceImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;

@ApplicationScoped
public class ScopeTypeHierarchyPersistenceImpl extends AbstractHierarchyPersistenceImpl<ScopeTypeHierarchy,ScopeType,ScopeTypeHierarchies> implements ScopeTypeHierarchyPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}
