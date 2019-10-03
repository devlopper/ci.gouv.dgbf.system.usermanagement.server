package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;

@Mapper
public abstract class UserAccountProfileDtoMapper extends AbstractMapperSourceDestinationImpl<UserAccountProfileDto, UserAccountProfile> {
	private static final long serialVersionUID = 1L;
 
}