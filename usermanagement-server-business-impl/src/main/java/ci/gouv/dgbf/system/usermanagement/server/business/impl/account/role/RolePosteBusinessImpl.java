package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

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
			String code = null;
			if(rolePoste.getMinistry() != null) {
				code = "MINISTERE"+ConstantCharacter.UNDESCORE+rolePoste.getMinistry().getIdentifier();
			}else if(rolePoste.getProgram() != null) {
				code = "PROGRAMME"+ConstantCharacter.UNDESCORE+rolePoste.getProgram().getIdentifier();
			}else if(rolePoste.getAdministrativeUnit() != null) {
				code = "UNITE_ADMINISTRATIVE"+ConstantCharacter.UNDESCORE+rolePoste.getAdministrativeUnit().getIdentifier();
			}
			rolePoste.setCode(rolePoste.getFunction().getCode()+(__injectStringHelper__().isBlank(code) ? ConstantEmpty.STRING : (ConstantCharacter.UNDESCORE+code)));
		}
		
		if(__injectStringHelper__().isBlank(rolePoste.getName())) {
			String name = null;
			if(rolePoste.getMinistry() != null) {
				name = "du ministère "+rolePoste.getMinistry().getIdentifier();
			}else if(rolePoste.getProgram() != null) {
				name = "du programme "+rolePoste.getProgram().getIdentifier();
			}else if(rolePoste.getAdministrativeUnit() != null) {
				name = "de l'unité administrative "+rolePoste.getAdministrativeUnit().getIdentifier();
			}
			rolePoste.setName(rolePoste.getFunction().getName()+(__injectStringHelper__().isBlank(name) ? ConstantEmpty.STRING : (ConstantCharacter.SPACE+name)));
		}
	}
	
	@Override
	protected Class<RolePoste> __getPersistenceEntityClass__() {
		return RolePoste.class;
	}
	
}
