package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.hierarchy.AbstractBusinessIdentifiedByStringAndCodedImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeHierarchyBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeProfileBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypeHierarchyPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeHierarchy;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeTypeProfile;

@ApplicationScoped
public class ScopeTypeBusinessImpl extends AbstractBusinessIdentifiedByStringAndCodedImpl<ScopeType, ScopeTypePersistence,ScopeTypeHierarchy,ScopeTypeHierarchies,ScopeTypeHierarchyPersistence,ScopeTypeHierarchyBusiness> implements ScopeTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateAfter__(ScopeType scopeType, Properties properties, BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(scopeType, properties, function);
		if(CollectionHelper.isNotEmpty(scopeType.getProfiles()))
			__inject__(ScopeTypeProfileBusiness.class).createMany(scopeType.getProfiles().stream().map(x -> new ScopeTypeProfile(scopeType,x)).collect(Collectors.toList()));
	}
	
}
