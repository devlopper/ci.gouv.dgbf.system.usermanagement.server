package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.instance.InstanceHelper;
import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Functions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=User.TABLE_NAME)
public class User extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	//Names
	@Column(name=COLUMN_FIRST_NAME) private String firstName;
	@Column(name=COLUMN_LAST_NAMES) private String lastNames;
	
	//Contacts
	@Column(name=COLUMN_ELECTRONIC_MAIL_ADDRESS) private String electronicMailAddress;
	@Column(name=COLUMN_PHONE_NUMBER) private String phoneNumber;
	
	/**/

	@Transient private String names;
	@Transient private Functions functions;
	
	@Override
	public User setIdentifier(String identifier) {
		return (User) super.setIdentifier(identifier);
	}
	
	public Functions getFunctions(Boolean injectIfNull) {
		return (Functions) __getInjectIfNull__(FIELD_FUNCTIONS, injectIfNull);
	}
	
	public User addFunctions(Collection<Function> functions) {
		getFunctions(Boolean.TRUE).add(functions);
		return this;
	}
	
	public User addFunctions(Function...functions) {
		getFunctions(Boolean.TRUE).add(functions);
		return this;
	}
	
	public User addFunctionsByCodes(Collection<String> functionsCodes) {
		if(__inject__(CollectionHelper.class).isNotEmpty(functionsCodes)) {
			for(String index : functionsCodes)
				addFunctions(__inject__(InstanceHelper.class).getByIdentifierBusiness(Function.class, index));
		}
		return this;
	}
	
	public User addFunctionsByCodes(String...functionsCodes) {
		return addFunctionsByCodes(__inject__(CollectionHelper.class).instanciate(functionsCodes));
	}
	
	/**/
	
	/**/
	
	public static final String FIELD_FIRST_NAME = "firstName";
	public static final String FIELD_LAST_NAMES = "lastNames";
	public static final String FIELD_ELECTRONIC_MAIL_ADDRESS = "electronicMailAddress";
	public static final String FIELD_PHONE_NUMBER = "phoneNumber";
	public static final String FIELD_NAMES = "names";
	public static final String FIELD_FUNCTIONS = "functions";
	
	public static final String TABLE_NAME = "util";
	
	public static final String COLUMN_FIRST_NAME = "nom";
	public static final String COLUMN_LAST_NAMES = "prenoms";
	public static final String COLUMN_ELECTRONIC_MAIL_ADDRESS = "adresse_electronique";
	public static final String COLUMN_PHONE_NUMBER = "numero_telephone";
	
}
