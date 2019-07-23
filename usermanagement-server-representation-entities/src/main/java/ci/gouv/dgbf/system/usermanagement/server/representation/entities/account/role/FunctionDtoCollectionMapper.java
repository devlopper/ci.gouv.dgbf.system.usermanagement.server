package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractEntityCollectionMapperImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;

@ApplicationScoped
public class FunctionDtoCollectionMapper extends AbstractEntityCollectionMapperImpl<FunctionDtoCollection,FunctionDto,Functions,Function> implements Serializable {
	private static final long serialVersionUID = 1L;


}