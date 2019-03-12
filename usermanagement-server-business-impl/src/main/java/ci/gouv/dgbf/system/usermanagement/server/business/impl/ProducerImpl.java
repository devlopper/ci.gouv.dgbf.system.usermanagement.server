package ci.gouv.dgbf.system.usermanagement.server.business.impl;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.cyk.utility.clazz.ClassHelper;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;

@Singleton
public class ProducerImpl extends org.cyk.utility.__kernel__.object.dynamic.AbstractProducerImpl implements Producer,Serializable {
	private static final long serialVersionUID = 1L;

	private Class<?> singleSignOnQualifierClass;
	
	@Override
	public Class<?> getSingleSignOnQualifierClass() {
		return singleSignOnQualifierClass;
	}
	
	@Override
	public Producer setSingleSignOnQualifierClass(Class<?> singleSignOnQualifierClass) {
		this.singleSignOnQualifierClass = singleSignOnQualifierClass;
		return this;
	}
	
	@Override
	protected Class<?> __getQualifierClass__(Class<?> aClass) {
		if(__inject__(ClassHelper.class).isInstanceOfOne(aClass,AccountBusiness.class))
			return getSingleSignOnQualifierClass();
		return super.__getQualifierClass__(aClass);
	}
	
	@Override
	@Produces @Singleton
	public AccountBusiness getAccountBusiness() {
		return __produce__(AccountBusiness.class);
	}

}
