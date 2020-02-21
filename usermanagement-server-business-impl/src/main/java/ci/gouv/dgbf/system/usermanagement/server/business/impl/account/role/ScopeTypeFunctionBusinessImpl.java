package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeFunction;

@ApplicationScoped
public class ScopeTypeFunctionBusinessImpl extends AbstractBusinessEntityImpl<ScopeTypeFunction, ScopeTypeFunctionPersistence> implements ScopeTypeFunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

}
