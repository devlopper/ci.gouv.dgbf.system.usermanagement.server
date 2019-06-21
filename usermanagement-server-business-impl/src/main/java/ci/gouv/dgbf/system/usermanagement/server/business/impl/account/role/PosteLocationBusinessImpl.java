package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PosteLocationBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PosteLocationPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocation;

@ApplicationScoped
public class PosteLocationBusinessImpl extends AbstractBusinessEntityImpl<PosteLocation, PosteLocationPersistence> implements PosteLocationBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<PosteLocation> __getPersistenceEntityClass__() {
		return PosteLocation.class;
	}
	
}
