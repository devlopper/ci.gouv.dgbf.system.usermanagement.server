package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractDtoCollectionMapperImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScopes;

@ApplicationScoped
public class FunctionScopeDtoCollectionMapper extends AbstractDtoCollectionMapperImpl<FunctionScopeDtoCollection,FunctionScopeDto,FunctionScopes,FunctionScope> {

	private static final long serialVersionUID = 1L;

}