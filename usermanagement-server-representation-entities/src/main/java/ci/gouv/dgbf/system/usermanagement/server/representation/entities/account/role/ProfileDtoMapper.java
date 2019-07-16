package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.mapping.MapperSourceDestination;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;

@Mapper(uses= {FunctionDtoCollectionMapper.class})
public interface ProfileDtoMapper extends MapperSourceDestination<ProfileDto, Profile> {
    
	/*@Override
	default ProfileDto getSource(Profile profile) {
		ProfileDto profileDto = new ProfileDto();
		profileDto.setIdentifier(profile.getIdentifier());
		profileDto.setCode(profile.getCode());
		profileDto.setName(profile.getName());
		if(profile.getFunctions() != null && profile.getFunctions().getSize() > 0) {
			FunctionDtoCollection functions = new FunctionDtoCollection();
			for(Function index : profile.getFunctions().get())
				functions.add(DependencyInjection.inject(FunctionDtoMapper.class).getSource(index));
			profileDto.setFunctions(functions);	
		}
		return profileDto;
	}
	
	@Override
	default Profile getDestination(ProfileDto profileDto) {
		Profile profile = new Profile();
		profile.setIdentifier(profileDto.getIdentifier());
		profile.setCode(profileDto.getCode());
		profile.setName(profileDto.getName());
		if(profileDto.getFunctions() != null && profileDto.getFunctions().getCollection() != null && !profileDto.getFunctions().getCollection().isEmpty()) {
			Functions functions = DependencyInjection.inject(Functions.class);
			for(FunctionDto index : profileDto.getFunctions().getCollection())
				functions.add(DependencyInjection.inject(FunctionDtoMapper.class).getDestination(index));
			profile.setFunctions(functions);	
		}
		return profile;
	}*/
 
}