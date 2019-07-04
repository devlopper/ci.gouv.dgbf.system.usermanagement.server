package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ResourceBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;

@ApplicationScoped
public class ResourceBusinessImpl extends AbstractBusinessEntityImpl<Resource, ResourcePersistence> implements ResourceBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Resource> __getPersistenceEntityClass__() {
		return Resource.class;
	}
	
}
