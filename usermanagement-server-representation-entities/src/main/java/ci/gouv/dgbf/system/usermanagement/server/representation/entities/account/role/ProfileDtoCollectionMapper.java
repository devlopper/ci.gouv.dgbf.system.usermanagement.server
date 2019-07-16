package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import javax.enterprise.context.ApplicationScoped;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profiles;

@ApplicationScoped
public class ProfileDtoCollectionMapper extends AbstractDtoCollectionMapper<ProfileDtoCollection,ProfileDto,Profiles,Profile> {
	private static final long serialVersionUID = 1L;

}