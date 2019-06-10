package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PosteLocationTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocationType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.PosteLocationTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationTypeDtoCollection;

@Singleton
public class PosteLocationTypeRepresentationImpl extends AbstractRepresentationEntityImpl<PosteLocationType,PosteLocationTypeBusiness,PosteLocationTypeDto,PosteLocationTypeDtoCollection> implements PosteLocationTypeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<PosteLocationType> getPersistenceEntityClass() {
		return PosteLocationType.class;
	}
	
}
