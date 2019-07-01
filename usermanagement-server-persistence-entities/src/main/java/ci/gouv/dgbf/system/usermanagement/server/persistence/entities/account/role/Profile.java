package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.cyk.utility.array.ArrayHelper;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=Profile.TABLE_NAME)
public class Profile extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient private Functions functions;
	
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
	
	/**/
	
	public static final String FIELD_FUNCTIONS = "functions";
	
	public static final String TABLE_NAME = "pfl";
	
}
