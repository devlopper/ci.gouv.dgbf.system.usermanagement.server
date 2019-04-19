package ci.gouv.dgbf.system.usermanagement.server.representation.impl;

import java.io.Serializable;

import org.cyk.utility.instance.AbstractInstanceBuilderFunctionRunnableImpl;
import org.cyk.utility.instance.InstanceHelper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.User;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserNaturalPerson;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserNaturalPersonDto;

public class InstanceBuilderFunctionRunnableImpl extends AbstractInstanceBuilderFunctionRunnableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __copy__(Object source, Object destination) {
		/*if(source instanceof UserAccountDto && destination instanceof UserAccount) {
			UserAccountDto representation = (UserAccountDto) source;
			UserAccount persistence = (UserAccount) destination;
			persistence.setCode(representation.getCode());
			persistence.setUser(__inject__(InstanceHelper.class).buildOne(User.class, representation.getUser()));
			
		}else if(source instanceof UserAccount && destination instanceof UserAccountDto) {
			UserAccount persistence = (UserAccount) source;
			UserAccountDto representation = (UserAccountDto) destination;
			representation.setIdentifier(persistence.getIdentifier().toString());
			representation.setCode(persistence.getCode());
			representation.setUser(__inject__(InstanceHelper.class).buildOne(UserDto.class, persistence.getUser()));
			representation.setAccount(__inject__(InstanceHelper.class).buildOne(AccountDto.class, persistence.getAccount()));
			
		}else if(source instanceof UserDto && destination instanceof User) {
			UserDto representation = (UserDto) source;
			User persistence = (User) destination;
			persistence.setCode(representation.getCode());
			persistence.setElectronicMailAddress(representation.getElectronicMailAddress());
			persistence.setPerson(__inject__(InstanceHelper.class).buildOne(UserNaturalPerson.class, representation.getPerson()));
			
		}else if(source instanceof User && destination instanceof UserDto) {
			User persistence = (User) source;
			UserDto representation = (UserDto) destination;
			representation.setIdentifier(persistence.getIdentifier().toString());
			representation.setCode(persistence.getCode());
			representation.setElectronicMailAddress(persistence.getElectronicMailAddress());
			representation.setPerson(__inject__(InstanceHelper.class).buildOne(UserNaturalPersonDto.class, persistence.getPerson()));
			
		}else if(source instanceof Account && destination instanceof AccountDto) {
			Account persistence = (Account) source;
			AccountDto representation = (AccountDto) destination;
			representation.setIdentifier(persistence.getIdentifier().toString());
			representation.setCode(persistence.getCode());
		}else*/
			super.__copy__(source, destination);
		
	}
	
}
