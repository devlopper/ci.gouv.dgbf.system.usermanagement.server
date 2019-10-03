package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.__kernel__.field.container.FieldContainerCollectionProfiles;

public interface FieldContainerProfiles extends FieldContainerCollectionProfiles<ProfileDto> {
	
	@Override
	default Class<ProfileDto> getProfileClass() {
		return ProfileDto.class;
	}
	
}
