package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntity;
import org.cyk.utility.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDtoCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserAccountDto extends AbstractEntityFromPersistenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserDto user;
	private AccountDto account;
	private RolePosteDtoCollection rolePostes;
	
	public UserDto getUser(Boolean injectIfNull) {
		return (UserDto) __getInjectIfNull__(FIELD_USER, injectIfNull);
	}
	
	public AccountDto getAccount(Boolean injectIfNull) {
		return (AccountDto) __getInjectIfNull__(FIELD_ACCOUNT, injectIfNull);
	}
	
	public UserAccountDto addRolePostesByCodes(Collection<String> rolePostesCodes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(rolePostesCodes)) {
			for(String index : rolePostesCodes)
				if(__inject__(StringHelper.class).isNotBlank(index))
					addRolePostes(new RolePosteDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addRolePostesByCodes(String...rolePostesCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(rolePostesCodes))
			addRolePostesByCodes(__inject__(CollectionHelper.class).instanciate(rolePostesCodes));
		return this;
	}
	
	public UserAccountDto addRolePostes(Collection<RolePosteDto> rolePostes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(rolePostes))
			getRolePostes(Boolean.TRUE).add(rolePostes);	
		return this;
	}
	
	public UserAccountDto addRolePostes(RolePosteDto...rolePostes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(rolePostes))
			addRolePostes(__inject__(CollectionHelper.class).instanciate(rolePostes));
		return this;
	}
	
	public RolePosteDtoCollection getRolePostes(Boolean instanciateIfNull) {
		return (RolePosteDtoCollection) __getInstanciateIfNull__(FIELD_ROLE_POSTES, instanciateIfNull);
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_ROLE_POSTES = "rolePostes";
	
}
