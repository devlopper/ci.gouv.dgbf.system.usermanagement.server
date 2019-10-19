package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.util.Collection;

import org.cyk.utility.__kernel__.instance.InstanceHelper;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;

@Mapper
public abstract class UserAccountDtoMapper extends AbstractMapperSourceDestinationImpl<UserAccountDto, UserAccount> {
	private static final long serialVersionUID = 1L;

	public Collection<Function> getFuntions(Collection<FunctionDto> dtos) {
    	return InstanceHelper.getIdentifiedFromIdentifiers(Function.class, dtos);
    }
	
	public Collection<Profile> getProfiles(Collection<ProfileDto> dtos) {
    	return InstanceHelper.getIdentifiedFromIdentifiers(Profile.class, dtos);
    }
	
	public Collection<Scope> getScopes(Collection<ScopeDto> dtos) {
    	return InstanceHelper.getIdentifiedFromIdentifiers(Scope.class, dtos);
    }
	
	public Collection<FunctionScope> getFunctionScopes(Collection<FunctionScopeDto> dtos) {
    	return InstanceHelper.getIdentifiedFromIdentifiers(FunctionScope.class, dtos);
    }
	
}