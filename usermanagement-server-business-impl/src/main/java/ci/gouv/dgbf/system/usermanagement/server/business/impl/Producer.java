package ci.gouv.dgbf.system.usermanagement.server.business.impl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;

public interface Producer extends org.cyk.utility.__kernel__.object.dynamic.Producer {

	Class<?> getSingleSignOnQualifierClass();
	Producer setSingleSignOnQualifierClass(Class<?> singleSignOnQualifierClass);
	
	AccountBusiness getAccountBusiness();
	
}
