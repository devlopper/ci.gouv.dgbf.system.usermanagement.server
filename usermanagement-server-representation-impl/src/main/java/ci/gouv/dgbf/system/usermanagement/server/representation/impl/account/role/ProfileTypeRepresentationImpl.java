package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfileTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileTypeDtoCollection;

@ApplicationScoped
public class ProfileTypeRepresentationImpl extends AbstractRepresentationEntityImpl<ProfileType,ProfileTypeBusiness,ProfileTypeDto,ProfileTypeDtoCollection> implements ProfileTypeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
