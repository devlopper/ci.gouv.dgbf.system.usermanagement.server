package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.MinistryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Ministry;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.MinistryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.MinistryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.MinistryDtoCollection;

@Singleton
public class MinistryRepresentationImpl extends AbstractRepresentationEntityImpl<Ministry,MinistryBusiness,MinistryDto,MinistryDtoCollection> implements MinistryRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<Ministry> getPersistenceEntityClass() {
		return Ministry.class;
	}
	
}
