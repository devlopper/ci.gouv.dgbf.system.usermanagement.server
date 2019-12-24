package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeProfile;

@ApplicationScoped
public class ScopeTypeProfileBusinessImpl extends AbstractBusinessEntityImpl<ScopeTypeProfile, ScopeTypeProfilePersistence> implements ScopeTypeProfileBusiness,Serializable {
	private static final long serialVersionUID = 1L;

}
