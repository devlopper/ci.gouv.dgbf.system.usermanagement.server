package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PosteLocationTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocationType;

@ApplicationScoped
public class PosteLocationTypePersistenceImpl extends AbstractPersistenceEntityImpl<PosteLocationType> implements PosteLocationTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	
}
