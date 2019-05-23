package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;

@Singleton
public class RolePosteBusinessImpl extends AbstractBusinessEntityImpl<RolePoste, RolePostePersistence> implements RolePosteBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<RolePoste> __getPersistenceEntityClass__() {
		return RolePoste.class;
	}
	
}
