package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountRolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountRolePoste;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountRolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountRolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountRolePosteDtoCollection;

@ApplicationScoped
public class UserAccountRolePosteRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountRolePoste,UserAccountRolePosteBusiness,UserAccountRolePosteDto,UserAccountRolePosteDtoCollection> implements UserAccountRolePosteRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<UserAccountRolePoste> getPersistenceEntityClass() {
		return UserAccountRolePoste.class;
	}
	
}
