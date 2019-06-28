package ci.gouv.dgbf.system.usermanagement.server.representation.impl;

import java.io.Serializable;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.instance.AbstractInstanceBuilderImpl;
import org.cyk.utility.instance.InstanceHelper;
import org.cyk.utility.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProfilePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.RolePostePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocation;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Profile;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PosteLocationTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDto;

@ci.gouv.dgbf.system.usermanagement.server.annotation.System
public class InstanceBuilderImpl extends AbstractInstanceBuilderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __copy__(Object source, Object destination, Properties properties) {
		//Persistence to Representation
		if(source instanceof PosteLocation && destination instanceof PosteLocationDto) {
			PosteLocation persistence = (PosteLocation) source;
			PosteLocationDto representation = (PosteLocationDto) destination;
			representation.setIdentifier(persistence.getIdentifier());
			representation.setName(persistence.getName()); 
			representation.setType(__inject__(InstanceHelper.class).buildOne(PosteLocationTypeDto.class, persistence.getType())); 
		}else if(source instanceof RoleCategory && destination instanceof RoleCategoryDto) {
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
			representation.setLocation(__inject__(InstanceHelper.class).buildOne(PosteLocationDto.class, persistence.getLocation()));
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
				for(RolePoste index : persistence.getPostes().get())
					representation.addPostes(__inject__(InstanceHelper.class).buildOne(RolePosteDto.class, index));
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
				for(RolePosteDto index : representation.getPostes().getCollection()) {
					RolePoste rolePoste = null;
					if(__inject__(StringHelper.class).isBlank(index.getIdentifier()))
						rolePoste = __inject__(RolePostePersistence.class).readOneByBusinessIdentifier(index.getCode());
					else
						rolePoste = __inject__(RolePostePersistence.class).readOneBySystemIdentifier(index.getIdentifier());
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
