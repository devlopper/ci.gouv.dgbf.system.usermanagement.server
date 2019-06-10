package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.cyk.utility.__kernel__.constant.ConstantCharacter;
import org.cyk.utility.__kernel__.constant.ConstantEmpty;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;

@Singleton
public class RolePosteBusinessImpl extends AbstractBusinessEntityImpl<RolePoste, RolePostePersistence> implements RolePosteBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateOneBefore__(RolePoste rolePoste, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateOneBefore__(rolePoste, properties, function);
		if(__injectStringHelper__().isBlank(rolePoste.getCode())) {
			if(rolePoste.getLocation() != null) {
				String code = rolePoste.getLocation().getType().getCode()+ConstantCharacter.UNDESCORE+rolePoste.getLocation().getIdentifier();
				rolePoste.setCode(rolePoste.getFunction().getCode()+(__injectStringHelper__().isBlank(code) ? ConstantEmpty.STRING : (ConstantCharacter.UNDESCORE+code)));
			}
		}
		
		if(__injectStringHelper__().isBlank(rolePoste.getName())) {
			if(rolePoste.getLocation() != null) {
				String name = rolePoste.getLocation().getType().getName().toLowerCase()+ConstantCharacter.SPACE+rolePoste.getLocation().getIdentifier();
				if(StringUtils.startsWithAny(name, "a","e","i","o","u")) {
					name = "de l' " + name;
				}else {
					name = "du " + name;
				}
				rolePoste.setName(rolePoste.getFunction().getName()+(__injectStringHelper__().isBlank(name) ? ConstantEmpty.STRING : (ConstantCharacter.SPACE+name)));	
			}
		}
	}
	
	@Override
	protected Class<RolePoste> __getPersistenceEntityClass__() {
		return RolePoste.class;
	}
	
}
