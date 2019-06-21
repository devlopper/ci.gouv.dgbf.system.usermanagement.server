package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDtoCollection;

@ApplicationScoped
public class RolePosteRepresentationImpl extends AbstractRepresentationEntityImpl<RolePoste,RolePosteBusiness,RolePosteDto,RolePosteDtoCollection> implements RolePosteRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RolePoste> getPersistenceEntityClass() {
		return RolePoste.class;
	}
	
}
