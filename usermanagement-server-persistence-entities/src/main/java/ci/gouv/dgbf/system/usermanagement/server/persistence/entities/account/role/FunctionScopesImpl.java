package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import org.cyk.utility.collection.AbstractCollectionInstanceImpl;

@Dependent
public class FunctionScopesImpl extends AbstractCollectionInstanceImpl<FunctionScope> implements FunctionScopes,Serializable {
	private static final long serialVersionUID = 1L;


}
