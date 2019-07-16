package ci.gouv.dgbf.system.usermanagement.server.representation.impl;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.cyk.utility.field.AbstractFieldValueCopyImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profiles;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScopes;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollection;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollection;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDtoCollection;

@ci.gouv.dgbf.system.usermanagement.server.annotation.System
public class FieldValueCopyImpl extends AbstractFieldValueCopyImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Object __processValue__(Field source, Field destination, Object value) {
		if(__injectFieldHelper__().getField(UserAccountDto.class, UserAccountDto.FIELD_FUNCTION_SCOPES).equals(source) 
				&& __injectFieldHelper__().getField(UserAccount.class, UserAccount.FIELD_FUNCTION_SCOPES).equals(destination)) {
			FunctionScopes functionScopes = null;
			FunctionScopeDtoCollection rolePosteDtoCollection = ((FunctionScopeDtoCollection) value);
			if(rolePosteDtoCollection != null && Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(rolePosteDtoCollection.getCollection()))) {
				functionScopes = __inject__(FunctionScopes.class);
				for(FunctionScopeDto index : rolePosteDtoCollection.getCollection()) {
					functionScopes.add(__inject__(FunctionScopePersistence.class).readByBusinessIdentifier(index.getCode()));
				}
			}
			return functionScopes;
		}else if(__injectFieldHelper__().getField(UserAccountDto.class, UserAccountDto.FIELD_PROFILES).equals(source) 
				&& __injectFieldHelper__().getField(UserAccount.class, UserAccount.FIELD_PROFILES).equals(destination)) {
			Profiles profiles = null;
			ProfileDtoCollection profileDtoCollection = ((ProfileDtoCollection) value);
			if(profileDtoCollection != null && Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(profileDtoCollection.getCollection()))) {
				profiles = __inject__(Profiles.class);
				for(ProfileDto index : profileDtoCollection.getCollection()) {
					profiles.add(__inject__(ProfilePersistence.class).readByBusinessIdentifier(index.getCode()));
				}
			}
			return profiles;
		}else if(__injectFieldHelper__().getField(ProfileDto.class, ProfileDto.FIELD_FUNCTIONS).equals(source) 
				&& __injectFieldHelper__().getField(Profile.class, Profile.FIELD_FUNCTIONS).equals(destination)) {
			Functions functions = null;
			FunctionDtoCollection functionDtoCollection = ((FunctionDtoCollection) value);
			if(functionDtoCollection != null && Boolean.TRUE.equals(__injectCollectionHelper__().isNotEmpty(functionDtoCollection.getCollection()))) {
				functions = __inject__(Functions.class);
				for(FunctionDto index : functionDtoCollection.getCollection()) {
					functions.add(__inject__(FunctionPersistence.class).readByBusinessIdentifier(index.getCode()));
				}
			}
			return functions;
		}else
			return super.__processValue__(source, destination, value);
	}

}