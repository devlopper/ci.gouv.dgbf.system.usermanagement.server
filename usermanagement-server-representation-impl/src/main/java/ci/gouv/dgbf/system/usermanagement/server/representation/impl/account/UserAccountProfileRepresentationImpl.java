package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountProfileRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountProfileDtoCollection;

@ApplicationScoped
public class UserAccountProfileRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountProfile,UserAccountProfileBusiness,UserAccountProfileDto,UserAccountProfileDtoCollection> implements UserAccountProfileRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<UserAccountProfile> getPersistenceEntityClass() {
		return UserAccountProfile.class;
	}
	
}
