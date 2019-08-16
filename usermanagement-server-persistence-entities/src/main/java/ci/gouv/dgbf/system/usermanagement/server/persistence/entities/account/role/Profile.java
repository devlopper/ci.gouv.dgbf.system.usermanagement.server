package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.instance.InstanceHelper;
import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=Profile.TABLE_NAME)
public class Profile extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_TYPE) @NotNull private ProfileType type;
	
	/*
	 * The functions covered by this profile
	 */
	@Transient private Functions functions;
	
	/*
	 * The privileges associated to this profile
	 */
	@Transient private Privileges privileges;
	
	/**/
	
	@Override
	public Profile setIdentifier(String identifier) {
		return (Profile) super.setIdentifier(identifier);
	}
	
	@Override
	public Profile setCode(String code) {
		return (Profile) super.setCode(code);
	}
	
	@Override
	public Profile setName(String name) {
		return (Profile) super.setName(name);
	}
	
	public Profile setTypeFromCode(String code) {
		setFromBusinessIdentifier(FIELD_TYPE, code);
		return this;
	}
	
	public Functions getFunctions(Boolean injectIfNull) {
		return (Functions) __getInjectIfNull__(FIELD_FUNCTIONS, injectIfNull);
	}
	
	public Profile addFunctions(Collection<Function> functions) {
		getFunctions(Boolean.TRUE).add(functions);
		return this;
	}
	
	public Profile addFunctions(Function...functions) {
		if(__inject__(ArrayHelper.class).isNotEmpty(functions)) {
			addFunctions(__inject__(CollectionHelper.class).instanciate(functions));
		}
		return this;
	}
	
	public Profile addFunctionsByCodes(Collection<String> codes) {
		if(Boolean.TRUE.equals(__inject__(CollectionHelper.class).isNotEmpty(codes)))
			for(String index : codes)
				getFunctions(Boolean.TRUE).add(__inject__(InstanceHelper.class).getByIdentifierBusiness(Function.class, index));
		return this;
	}
	
	public Profile addFunctionsByCodes(String... codes) {
		addFunctionsByCodes(__inject__(CollectionHelper.class).instanciate(codes));
		return this;
	}
	
	public Privileges getPrivileges(Boolean injectIfNull) {
		return (Privileges) __getInjectIfNull__(FIELD_PRIVILEGES, injectIfNull);
	}
	
	public Profile addPrivileges(Collection<Privilege> privileges) {
		getPrivileges(Boolean.TRUE).add(privileges);
		return this;
	}
	
	public Profile addPrivileges(Privilege...privileges) {
		if(__inject__(ArrayHelper.class).isNotEmpty(privileges)) {
			addPrivileges(__inject__(CollectionHelper.class).instanciate(privileges));
		}
		return this;
	}
	
	public Profile addPrivilegesByCodes(Collection<String> codes) {
		if(Boolean.TRUE.equals(__inject__(CollectionHelper.class).isNotEmpty(codes)))
			for(String index : codes)
				getPrivileges(Boolean.TRUE).add(__inject__(InstanceHelper.class).getByIdentifierBusiness(Privilege.class, index));
		return this;
	}
	
	public Profile addPrivilegesByCodes(String... codes) {
		addPrivilegesByCodes(__inject__(CollectionHelper.class).instanciate(codes));
		return this;
	}
	
	/**/
	
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_FUNCTIONS = "functions";
	public static final String FIELD_PRIVILEGES = "privileges";
	
	public static final String TABLE_NAME = "pfl";
	
	public static final String COLUMN_TYPE = "type";
	
	/**/
	
}
