package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import javax.enterprise.context.ApplicationScoped;

import org.mapstruct.TargetType;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;

@ApplicationScoped // CDI component model
public class FunctionDtoCollectionMapper {

    public Functions resolve(FunctionDtoCollection functionDtoCollection, @TargetType Class<?> klass) {
        return null;
    }

    public FunctionDtoCollection to(Functions functions) {
        return null;
    }
}