package ci.gouv.dgbf.system.usermanagement.server.persistence.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.__kernel__.klass.PersistableClassesGetter;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountFunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterimModel;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeTypeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfilePrivilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Resource;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Service;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ServiceResource;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		PersistableClassesGetter.COLLECTION.set(List.of(UserAccountInterim.class,UserAccountInterimModel.class,UserAccountFunctionScope.class,UserAccountScope.class
				,UserAccountProfile.class,UserAccount.class,UserFunction.class,User.class,Account.class,ScopeTypeFunction.class,ProfilePrivilege.class,ProfileFunction.class,Profile.class
				,ProfileType.class,FunctionScope.class,Function.class,FunctionType.class,ScopeHierarchy.class,Scope.class,ScopeTypeHierarchy.class,ScopeType.class
				,PrivilegeHierarchy.class,Privilege.class,PrivilegeTypeHierarchy.class,PrivilegeType.class,ServiceResource.class,Service.class,Resource.class));
		__inject__(org.cyk.utility.server.persistence.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
		__inject__(ci.gouv.dgbf.system.usermanagement.server.persistence.entities.ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
