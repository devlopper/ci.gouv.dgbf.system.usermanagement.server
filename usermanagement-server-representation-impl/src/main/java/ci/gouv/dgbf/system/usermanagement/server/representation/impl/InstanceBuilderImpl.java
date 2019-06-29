package ci.gouv.dgbf.system.usermanagement.server.representation.impl;

import java.io.Serializable;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.instance.AbstractInstanceBuilderImpl;
import org.cyk.utility.instance.InstanceHelper;
import org.cyk.utility.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionScopeDto;

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
			representation.setType(__inject__(InstanceHelper.class).buildOne(ScopeTypeDto.class, persistence.getType())); 
		}else if(source instanceof RoleCategory && destination instanceof RoleCategoryDto) {
			RoleCategory persistence = (RoleCategory) source;
			RoleCategoryDto representation = (RoleCategoryDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
		}else if(source instanceof Function && destination instanceof FunctionDto) {
			Function persistence = (Function) source;
			FunctionDto representation = (FunctionDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
			representation.setCategory(__inject__(InstanceHelper.class).buildOne(RoleCategoryDto.class, persistence.getCategory())); 
		}else if(source instanceof FunctionScope && destination instanceof FunctionScopeDto) {
			FunctionScope persistence = (FunctionScope) source;
			FunctionScopeDto representation = (FunctionScopeDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
			representation.setFunction(__inject__(InstanceHelper.class).buildOne(FunctionDto.class, persistence.getFunction()));
			representation.setScope(__inject__(InstanceHelper.class).buildOne(ScopeDto.class, persistence.getScope()));
			/*
			representation.setMinistry(__inject__(InstanceHelper.class).buildOne(MinistryDto.class, persistence.getMinistry()));
			representation.setProgram(__inject__(InstanceHelper.class).buildOne(ProgramDto.class, persistence.getProgram()));
			representation.setAdministrativeUnit(__inject__(InstanceHelper.class).buildOne(AdministrativeUnitDto.class, persistence.getAdministrativeUnit()));
			*/
		}else if(source instanceof UserAccount && destination instanceof UserAccountDto) {
			UserAccount persistence = (UserAccount) source;
			UserAccountDto representation = (UserAccountDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setUser(__inject__(InstanceHelper.class).buildOne(UserDto.class, persistence.getUser()));
			representation.setAccount(__inject__(InstanceHelper.class).buildOne(AccountDto.class, persistence.getAccount()));
			if(__injectCollectionHelper__().isNotEmpty(persistence.getPostes()))
				for(FunctionScope index : persistence.getPostes().get())
					representation.addPostes(__inject__(InstanceHelper.class).buildOne(FunctionScopeDto.class, index));
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
			persistence.setPostes(null).setProfiles(null);
			if(representation.getPostes()!=null && __injectCollectionHelper__().isNotEmpty(representation.getPostes().getCollection()))
				for(FunctionScopeDto index : representation.getPostes().getCollection()) {
					FunctionScope rolePoste = null;
					if(__inject__(StringHelper.class).isBlank(index.getIdentifier()))
						rolePoste = __inject__(FunctionScopePersistence.class).readOneByBusinessIdentifier(index.getCode());
					else
						rolePoste = __inject__(FunctionScopePersistence.class).readOneBySystemIdentifier(index.getIdentifier());
					if(rolePoste != null)
						persistence.getPostes(Boolean.TRUE).add(rolePoste);
				}
			if(representation.getProfiles()!=null && __injectCollectionHelper__().isNotEmpty(representation.getProfiles().getCollection()))
				for(ProfileDto index : representation.getProfiles().getCollection()) {
					Profile profile = null;
					if(__inject__(StringHelper.class).isBlank(index.getIdentifier()))
						profile = __inject__(ProfilePersistence.class).readOneByBusinessIdentifier(index.getCode());
					else
						profile = __inject__(ProfilePersistence.class).readOneBySystemIdentifier(index.getIdentifier());
					if(profile != null)
						persistence.getProfiles(Boolean.TRUE).add(profile);
				}
		}
		
		else
			super.__copy__(source, destination, properties);
	}
	
}
