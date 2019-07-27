package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;

@ApplicationScoped
public class FunctionTypePersistenceImpl extends AbstractPersistenceEntityImpl<FunctionType> implements FunctionTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<FunctionType> getEntityClass() {
		return FunctionType.class;
	}
}
