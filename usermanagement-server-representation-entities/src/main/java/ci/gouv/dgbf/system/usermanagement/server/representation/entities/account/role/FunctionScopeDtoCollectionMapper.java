package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import javax.enterprise.context.ApplicationScoped;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScopes;

@ApplicationScoped
public class FunctionScopeDtoCollectionMapper extends AbstractDtoCollectionMapper<FunctionScopeDtoCollection,FunctionScopeDto,FunctionScopes,FunctionScope> {

	private static final long serialVersionUID = 1L;

}