package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PosteLocationBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.PosteLocationRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationDtoCollection;

@Singleton
public class PosteLocationRepresentationImpl extends AbstractRepresentationEntityImpl<PosteLocation,PosteLocationBusiness,PosteLocationDto,PosteLocationDtoCollection> implements PosteLocationRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<PosteLocation> getPersistenceEntityClass() {
		return PosteLocation.class;
	}
	
}
