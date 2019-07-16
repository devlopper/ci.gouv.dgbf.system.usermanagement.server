package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import javax.enterprise.context.ApplicationScoped;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;

@ApplicationScoped
public class FunctionDtoCollectionMapper extends AbstractDtoCollectionMapper<FunctionDtoCollection,FunctionDto,Functions,Function> {
	private static final long serialVersionUID = 1L;


}