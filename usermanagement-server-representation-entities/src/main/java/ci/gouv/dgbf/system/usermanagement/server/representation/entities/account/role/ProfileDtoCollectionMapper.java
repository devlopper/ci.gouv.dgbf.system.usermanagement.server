package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import javax.enterprise.context.ApplicationScoped;

import org.mapstruct.TargetType;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profiles;

@ApplicationScoped // CDI component model
public class ProfileDtoCollectionMapper {

    public Profiles resolve(ProfileDtoCollection profileDtoCollection, @TargetType Class<?> klass) {
        return null;
    }

    public ProfileDtoCollection to(Profiles profiles) {
        return null;
    }
}