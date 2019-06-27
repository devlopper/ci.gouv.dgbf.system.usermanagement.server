package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollection;

@ApplicationScoped
public class ProfileRepresentationImpl extends AbstractRepresentationEntityImpl<Profile,ProfileBusiness,ProfileDto,ProfileDtoCollection> implements ProfileRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<Profile> getPersistenceEntityClass() {
		return Profile.class;
	}
	
}
