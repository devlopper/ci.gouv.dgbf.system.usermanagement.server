package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ResourcePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;

@ApplicationScoped
public class ResourcePersistenceImpl extends AbstractPersistenceEntityImpl<Resource> implements ResourcePersistence,Serializable {
	private static final long serialVersionUID = 1L;

}
