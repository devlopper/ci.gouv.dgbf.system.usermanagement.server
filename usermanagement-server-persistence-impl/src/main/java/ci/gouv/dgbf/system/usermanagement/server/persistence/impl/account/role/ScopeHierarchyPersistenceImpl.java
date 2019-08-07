package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractHierarchyPersistenceImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchy;

@ApplicationScoped
public class ScopeHierarchyPersistenceImpl extends AbstractHierarchyPersistenceImpl<ScopeHierarchy,Scope,ScopeHierarchies> implements ScopeHierarchyPersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
}