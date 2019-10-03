package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractEntityCollectionMapperImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScopes;

@ApplicationScoped @Deprecated
public class FunctionScopeDtoCollectionMapper extends AbstractEntityCollectionMapperImpl<FunctionScopeDtoCollection,FunctionScopeDto,FunctionScopes,FunctionScope> implements Serializable {
	private static final long serialVersionUID = 1L;

}