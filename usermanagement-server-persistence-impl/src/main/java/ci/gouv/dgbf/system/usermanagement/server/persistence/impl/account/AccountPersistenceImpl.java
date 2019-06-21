package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.AccountPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;

@ApplicationScoped
public class AccountPersistenceImpl extends AbstractPersistenceEntityImpl<Account> implements AccountPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	//private String readByCodeByPass;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		/*addQueryCollectInstances(readByCodeByPass, __instanciateQuerySelect__()
				.getWherePredicateBuilderAsGroup().addOperandBuilderByAttribute(Account.FIELD_CODE,ComparisonOperator.EQ).and()
				.addOperandBuilderByAttribute(Account.FIELD_PASS,ComparisonOperator.EQ)
				.getParentAsWhereClause().getParentAs(QueryStringBuilderSelect.class));
		*/
	}
	/*
	@Override
	protected Object[] __getQueryParameters__(String queryIdentifier, Object... objects) {
		PersistenceQuery persistenceQuery = __inject__(PersistenceQueryRepository.class).getBySystemIdentifier(queryIdentifier);
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByCodeByPass,queryIdentifier))
			return new Object[]{Account.FIELD_CODE,objects[0],Account.FIELD_PASS,objects[1]};
		
		return super.__getQueryParameters__(queryIdentifier, objects);
	}
	*/
	
	@Override
	public Account readByCodeByPass(String code, String pass) {
		return __readOne__(____getQueryParameters____(null,code,pass));
	}
	

}
