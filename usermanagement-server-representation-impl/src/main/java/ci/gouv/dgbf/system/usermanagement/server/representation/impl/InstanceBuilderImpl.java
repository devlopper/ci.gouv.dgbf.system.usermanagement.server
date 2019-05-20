package ci.gouv.dgbf.system.usermanagement.server.representation.impl;

import java.io.Serializable;

import org.cyk.utility.__kernel__.annotation.Representation;
import org.cyk.utility.__kernel__.annotation.Server;
import org.cyk.utility.__kernel__.annotation.System;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.instance.AbstractInstanceBuilderImpl;
import org.cyk.utility.instance.InstanceHelper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;

@System @Server @Representation
public class InstanceBuilderImpl extends AbstractInstanceBuilderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __copy__(Object source, Object destination, Properties properties) {
		//Persistence to Representation
		if(source instanceof RoleCategory && destination instanceof RoleCategoryDto) {
			RoleCategory persistence = (RoleCategory) source;
			RoleCategoryDto representation = (RoleCategoryDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
		}else if(source instanceof RoleFunction && destination instanceof RoleFunctionDto) {
			RoleFunction persistence = (RoleFunction) source;
			RoleFunctionDto representation = (RoleFunctionDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
			representation.setCategory(__inject__(InstanceHelper.class).buildOne(RoleCategoryDto.class, persistence.getCategory())); 
		}else if(source instanceof RolePoste && destination instanceof RolePosteDto) {
			RolePoste persistence = (RolePoste) source;
			RolePosteDto representation = (RolePosteDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setCode(persistence.getCode());
			representation.setName(persistence.getName()); 
			representation.setFunction(__inject__(InstanceHelper.class).buildOne(RoleFunctionDto.class, persistence.getFunction())); 
		}else if(source instanceof UserAccount && destination instanceof UserAccountDto) {
			UserAccount persistence = (UserAccount) source;
			UserAccountDto representation = (UserAccountDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setUser(__inject__(InstanceHelper.class).buildOne(UserDto.class, persistence.getUser()));
			representation.setAccount(__inject__(InstanceHelper.class).buildOne(AccountDto.class, persistence.getAccount()));
			if(__injectCollectionHelper__().isNotEmpty(persistence.getRolePostes()))
				for(RolePoste index : persistence.getRolePostes().get())
					representation.addRolePostes(index.getCode());
		}
		//Representation to Persistence
		else if(source instanceof UserAccountDto && destination instanceof UserAccount) {
			UserAccount persistence = (UserAccount) destination;
			UserAccountDto representation = (UserAccountDto) source;
			persistence.setIdentifier(representation.getIdentifier());
			persistence.setUser(__inject__(InstanceHelper.class).buildOne(User.class, representation.getUser()));
			persistence.setAccount(__inject__(InstanceHelper.class).buildOne(Account.class, representation.getAccount()));
			if(representation.getRolePostes()!=null)
				for(RolePosteDto index : representation.getRolePostes().getCollection())
					persistence.addRolePostes(__inject__(RolePostePersistence.class).readOneByBusinessIdentifier(index.getCode()));
		}
		
		else
			super.__copy__(source, destination, properties);
	}
	
}
