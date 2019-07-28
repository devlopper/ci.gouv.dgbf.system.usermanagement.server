package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;

@ApplicationScoped
public class FunctionPersistenceImpl extends AbstractPersistenceEntityImpl<Function> implements FunctionPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}
