package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractEntityCollectionMapperImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scopes;

@ApplicationScoped @Deprecated
public class ScopeDtoCollectionMapper extends AbstractEntityCollectionMapperImpl<ScopeDtoCollection,ScopeDto,Scopes,Scope> implements Serializable {
	private static final long serialVersionUID = 1L;

}