package ci.gouv.dgbf.system.usermanagement.server.representation.impl;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.cyk.utility.field.AbstractFieldValueCopyImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePostes;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDtoCollection;

@ci.gouv.dgbf.system.usermanagement.server.annotation.System
public class FieldValueCopyImpl extends AbstractFieldValueCopyImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Object __processValue__(Field source, Field destination, Object value) {
		if(__injectFieldHelper__().getField(UserAccountDto.class, UserAccountDto.FIELD_ROLE_POSTES).equals(source) 
				&& __injectFieldHelper__().getField(UserAccount.class, UserAccount.FIELD_ROLE_POSTES).equals(destination)) {
			RolePostes rolePostes = null;
			RolePosteDtoCollection rolePosteDtoCollection = ((RolePosteDtoCollection) value);
			if(rolePosteDtoCollection != null && Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(rolePosteDtoCollection.getCollection()))) {
				rolePostes = __inject__(RolePostes.class);
				for(RolePosteDto index : rolePosteDtoCollection.getCollection()) {
					rolePostes.add(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier(index.getCode()));
				}
			}
			return rolePostes;
		}else
			return super.__processValue__(source, destination, value);
	}

}