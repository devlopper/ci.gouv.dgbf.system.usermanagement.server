package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractEntityCollectionMapperImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profiles;

@ApplicationScoped
public class ProfileDtoCollectionMapper extends AbstractEntityCollectionMapperImpl<ProfileDtoCollection,ProfileDto,Profiles,Profile> implements Serializable {
	private static final long serialVersionUID = 1L;

}