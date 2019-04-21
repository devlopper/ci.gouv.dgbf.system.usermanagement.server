package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDtoCollection;

@Singleton
public class RolePosteRepresentationImpl extends AbstractRepresentationEntityImpl<RolePoste,RolePosteBusiness,RolePosteDto,RolePosteDtoCollection> implements RolePosteRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RolePoste> getPersistenceEntityClass() {
		return RolePoste.class;
	}
	
}
