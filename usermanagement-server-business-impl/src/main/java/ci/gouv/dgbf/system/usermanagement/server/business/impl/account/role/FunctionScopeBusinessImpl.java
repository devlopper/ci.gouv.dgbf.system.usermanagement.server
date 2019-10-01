package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.constant.ConstantCharacter;
import org.cyk.utility.__kernel__.constant.ConstantEmpty;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;

@ApplicationScoped
public class FunctionScopeBusinessImpl extends AbstractBusinessEntityImpl<FunctionScope, FunctionScopePersistence> implements FunctionScopeBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(FunctionScope functionScope, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(functionScope, properties, function);
		if(StringHelper.isBlank(functionScope.getCode())) {
			if(functionScope.getScope() != null) {
				String code = functionScope.getScope().getType().getCode()+ConstantCharacter.UNDESCORE+functionScope.getScope().getIdentifier();
				functionScope.setCode(functionScope.getFunction().getCode()+(StringHelper.isBlank(code) ? ConstantEmpty.STRING : (ConstantCharacter.UNDESCORE+code)));
			}
		}
		
		if(StringHelper.isBlank(functionScope.getName())) {
			if(functionScope.getScope() != null) {
				String name = functionScope.getFunction().getName();
				if(!name.toLowerCase().endsWith(functionScope.getScope().getType().getName().toLowerCase())) {
					name = name + ConstantCharacter.SPACE + functionScope.getScope().getType().getName().toLowerCase();
				}
				name = name+ConstantCharacter.SPACE+functionScope.getScope().getIdentifier();
				/*
				if(StringUtils.startsWithAny(name, "a","e","i","o","u")) {
					name = "de l' " + name;
				}else {
					name = "du " + name;
				}
				*/
				functionScope.setName(name);
				//functionScope.setName(functionScope.getFunction().getName()+(StringHelper.isBlank(name) ? ConstantEmpty.STRING : (ConstantCharacter.SPACE+name)));	
			}
		}
	}
	
}
