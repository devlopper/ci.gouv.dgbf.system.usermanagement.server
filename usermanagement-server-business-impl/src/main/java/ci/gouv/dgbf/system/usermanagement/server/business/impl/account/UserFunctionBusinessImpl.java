package ci.gouv.dgbf.system.usermanagement.server.business.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.UserFunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;

@ApplicationScoped
public class UserFunctionBusinessImpl extends AbstractBusinessEntityImpl<UserFunction, UserFunctionPersistence> implements UserFunctionBusiness,Serializable {
	private static final long serialVersionUID = 1L;

}
