package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeDtoCollectionMapper;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollectionMapper;

@Mapper(uses= {FunctionDtoCollectionMapper.class,ProfileDtoCollectionMapper.class,PrivilegeDtoCollectionMapper.class,FunctionScopeDtoCollectionMapper.class})
public abstract class UserAccountProfileDtoMapper extends AbstractMapperSourceDestinationImpl<UserAccountProfileDto, UserAccountProfile> {
	private static final long serialVersionUID = 1L;
 
}