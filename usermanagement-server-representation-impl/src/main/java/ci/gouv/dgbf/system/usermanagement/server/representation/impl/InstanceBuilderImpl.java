package ci.gouv.dgbf.system.usermanagement.server.representation.impl;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.instance.AbstractInstanceBuilderImpl;
import org.cyk.utility.instance.InstanceHelper;
import org.cyk.utility.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;

@ci.gouv.dgbf.system.usermanagement.server.annotation.System
public class InstanceBuilderImpl extends AbstractInstanceBuilderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __copy__(Object source, Object destination, Properties properties) {
		//Persistence to Representation
		if(source instanceof Scope && destination instanceof ScopeDto) {
			Scope persistence = (Scope) source;
			ScopeDto representation = (ScopeDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setName(persistence.getName()); 
			//representation.setType(__inject__(InstanceHelper.class).buildOne(ScopeTypeDto.class, persistence.getType())); 
		}else if(source instanceof FunctionCategory && destination instanceof FunctionCategoryDto) {
			FunctionCategory persistence = (FunctionCategory) source;
			FunctionCategoryDto representation = (FunctionCategoryDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
		}else if(source instanceof Function && destination instanceof FunctionDto) {
			Function persistence = (Function) source;
			FunctionDto representation = (FunctionDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
			//representation.setCategory(__inject__(InstanceHelper.class).buildOne(FunctionCategoryDto.class, persistence.getCategory())); 
		}else if(source instanceof FunctionScope && destination instanceof FunctionScopeDto) {
			FunctionScope persistence = (FunctionScope) source;
			FunctionScopeDto representation = (FunctionScopeDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
			//representation.setFunction(__inject__(InstanceHelper.class).buildOne(FunctionDto.class, persistence.getFunction()));
			//representation.setScope(__inject__(InstanceHelper.class).buildOne(ScopeDto.class, persistence.getScope()));
		}else if(source instanceof Profile && destination instanceof ProfileDto) {
			Profile persistence = (Profile) source;
			ProfileDto representation = (ProfileDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
			if(__injectCollectionHelper__().isNotEmpty(persistence.getFunctions()))
				for(Function index : persistence.getFunctions().get())
					representation.addFunctions(__inject__(InstanceHelper.class).buildOne(FunctionDto.class, index));
		}else if(source instanceof ProfileFunction && destination instanceof ProfileFunctionDto) {
			ProfileFunction persistence = (ProfileFunction) source;
			ProfileFunctionDto representation = (ProfileFunctionDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			@SuppressWarnings("unchecked")
			Collection<String> fields = (Collection<String>) properties.getFields();
			if(__inject__(CollectionHelper.class).contains(fields, ProfileFunction.FIELD_PROFILE))
				representation.setProfile(__inject__(InstanceHelper.class).buildOne(ProfileDto.class, persistence.getProfile()));
			if(__inject__(CollectionHelper.class).contains(fields, ProfileFunction.FIELD_FUNCTION))
				representation.setFunction(__inject__(InstanceHelper.class).buildOne(FunctionDto.class, persistence.getFunction()));
		}else if(source instanceof UserAccount && destination instanceof UserAccountDto) {
			UserAccount persistence = (UserAccount) source;
			UserAccountDto representation = (UserAccountDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			//representation.setUser(__inject__(InstanceHelper.class).buildOne(UserDto.class, persistence.getUser()));
			//representation.setAccount(__inject__(InstanceHelper.class).buildOne(AccountDto.class, persistence.getAccount()));
			if(__injectCollectionHelper__().isNotEmpty(persistence.getFunctionScopes()))
				for(FunctionScope index : persistence.getFunctionScopes().get())
					representation.addFunctionScopes(__inject__(InstanceHelper.class).buildOne(FunctionScopeDto.class, index));
			if(__injectCollectionHelper__().isNotEmpty(persistence.getProfiles()))
				for(Profile index : persistence.getProfiles().get())
					representation.addProfiles(__inject__(InstanceHelper.class).buildOne(ProfileDto.class, index));
		}
		//Representation to Persistence
		else if(source instanceof UserAccountDto && destination instanceof UserAccount) {
			UserAccountDto representation = (UserAccountDto) source;
			UserAccount persistence = (UserAccount) destination;
			persistence.setIdentifier(representation.getIdentifier());
			persistence.setUser(__inject__(InstanceHelper.class).buildOne(User.class, representation.getUser()));
			persistence.setAccount(__inject__(InstanceHelper.class).buildOne(Account.class, representation.getAccount()));
			persistence.setFunctionScopes(null).setProfiles(null);
			if(representation.getFunctionScopes()!=null && __injectCollectionHelper__().isNotEmpty(representation.getFunctionScopes().getCollection()))
				for(FunctionScopeDto index : representation.getFunctionScopes().getCollection()) {
					FunctionScope functionScope = null;
					if(__inject__(StringHelper.class).isBlank(index.getIdentifier()))
						functionScope = __inject__(FunctionScopePersistence.class).readByBusinessIdentifier(index.getCode());
					else
						functionScope = __inject__(FunctionScopePersistence.class).readBySystemIdentifier(index.getIdentifier());
					if(functionScope != null)
						persistence.getFunctionScopes(Boolean.TRUE).add(functionScope);
				}
			if(representation.getProfiles()!=null && __injectCollectionHelper__().isNotEmpty(representation.getProfiles().getCollection()))
				for(ProfileDto index : representation.getProfiles().getCollection()) {
					Profile profile = null;
					if(__inject__(StringHelper.class).isBlank(index.getIdentifier()))
						profile = __inject__(ProfilePersistence.class).readByBusinessIdentifier(index.getCode());
					else
						profile = __inject__(ProfilePersistence.class).readBySystemIdentifier(index.getIdentifier());
					if(profile != null)
						persistence.getProfiles(Boolean.TRUE).add(profile);
				}
		}else if(source instanceof ProfileDto && destination instanceof Profile) {
			ProfileDto representation = (ProfileDto) source;
			Profile persistence = (Profile) destination;
			persistence.setIdentifier(representation.getIdentifier());
			persistence.setCode(representation.getCode());
			persistence.setName(representation.getName());
			persistence.setFunctions(null);
			if(representation.getFunctions()!=null && __injectCollectionHelper__().isNotEmpty(representation.getFunctions().getCollection()))
				for(FunctionDto index : representation.getFunctions().getCollection()) {
					Function function = null;
					if(__inject__(StringHelper.class).isBlank(index.getIdentifier()))
						function = __inject__(FunctionPersistence.class).readByBusinessIdentifier(index.getCode());
					else
						function = __inject__(FunctionPersistence.class).readBySystemIdentifier(index.getIdentifier());
					if(function != null)
						persistence.getFunctions(Boolean.TRUE).add(function);
				}
		}else
			super.__copy__(source, destination, properties);
	}
	
}
