package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import javax.enterprise.context.ApplicationScoped;

import org.mapstruct.TargetType;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScopes;

@ApplicationScoped // CDI component model
public class FunctionScopeDtoCollectionMapper {

    public FunctionScopes resolve(FunctionScopeDtoCollection functionScopeDtoCollection, @TargetType Class<?> klass) {
        return null;
    }

    public FunctionScopeDtoCollection to(FunctionScopes functionScopes) {
        return null;
    }
}