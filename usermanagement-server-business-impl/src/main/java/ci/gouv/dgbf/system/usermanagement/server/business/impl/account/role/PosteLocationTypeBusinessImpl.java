package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PosteLocationTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PosteLocationTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocationType;

@Singleton
public class PosteLocationTypeBusinessImpl extends AbstractBusinessEntityImpl<PosteLocationType, PosteLocationTypePersistence> implements PosteLocationTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<PosteLocationType> __getPersistenceEntityClass__() {
		return PosteLocationType.class;
	}
	
}
