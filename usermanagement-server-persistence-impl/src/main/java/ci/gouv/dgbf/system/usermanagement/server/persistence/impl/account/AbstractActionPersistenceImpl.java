package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.ActionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Action;

public abstract class AbstractActionPersistenceImpl extends AbstractPersistenceEntityImpl<Action> implements ActionPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<Action> getEntityClass() {
		return Action.class;
	}
	
}
