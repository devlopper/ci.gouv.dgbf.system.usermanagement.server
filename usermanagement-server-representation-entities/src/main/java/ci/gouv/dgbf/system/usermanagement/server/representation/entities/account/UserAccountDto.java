package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntity;
import org.cyk.utility.string.StringHelper;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfileDtoCollection;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleFunctionDtoCollection;
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
	private RoleFunctionDtoCollection functions;
	private ProfileDtoCollection profiles;
	private RolePosteDtoCollection postes;
	
	public UserDto getUser(Boolean injectIfNull) {
		return (UserDto) __getInjectIfNull__(FIELD_USER, injectIfNull);
	}
	
	public AccountDto getAccount(Boolean injectIfNull) {
		return (AccountDto) __getInjectIfNull__(FIELD_ACCOUNT, injectIfNull);
	}
	
	public UserAccountDto addPostesByCodes(Collection<String> rolePostesCodes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(rolePostesCodes)) {
			for(String index : rolePostesCodes)
				if(__inject__(StringHelper.class).isNotBlank(index))
					addPostes(new RolePosteDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addPostesByCodes(String...rolePostesCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(rolePostesCodes))
			addPostesByCodes(__inject__(CollectionHelper.class).instanciate(rolePostesCodes));
		return this;
	}
	
	public UserAccountDto addPostes(Collection<RolePosteDto> rolePostes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(rolePostes))
			getPostes(Boolean.TRUE).add(rolePostes);	
		return this;
	}
	
	public UserAccountDto addPostes(RolePosteDto...rolePostes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(rolePostes))
			addPostes(__inject__(CollectionHelper.class).instanciate(rolePostes));
		return this;
	}
	
	public RolePosteDtoCollection getPostes(Boolean instanciateIfNull) {
		return (RolePosteDtoCollection) __getInstanciateIfNull__(FIELD_POSTES, instanciateIfNull);
	}
	
	/**/
	
	public UserAccountDto addProfilesByCodes(Collection<String> profilesCodes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(profilesCodes)) {
			for(String index : profilesCodes)
				if(__inject__(StringHelper.class).isNotBlank(index))
					addProfiles(new ProfileDto().setCode(index));
		}
		return this;
	}
	
	public UserAccountDto addProfilesByCodes(String...profilesCodes) {
		if(__inject__(ArrayHelper.class).isNotEmpty(profilesCodes))
			addProfilesByCodes(__inject__(CollectionHelper.class).instanciate(profilesCodes));
		return this;
	}
	
	public UserAccountDto addProfiles(Collection<ProfileDto> profiles) {
		if(__inject__(CollectionHelper.class).isNotEmpty(profiles))
			getProfiles(Boolean.TRUE).add(profiles);	
		return this;
	}
	
	public UserAccountDto addProfiles(ProfileDto...profiles) {
		if(__inject__(ArrayHelper.class).isNotEmpty(profiles))
			addProfiles(__inject__(CollectionHelper.class).instanciate(profiles));
		return this;
	}
	
	public ProfileDtoCollection getProfiles(Boolean instanciateIfNull) {
		return (ProfileDtoCollection) __getInstanciateIfNull__(FIELD_PROFILES, instanciateIfNull);
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_FUNCTIONS = "functions";
	public static final String FIELD_PROFILES = "profiles";
	public static final String FIELD_POSTES = "postes";
	
}
