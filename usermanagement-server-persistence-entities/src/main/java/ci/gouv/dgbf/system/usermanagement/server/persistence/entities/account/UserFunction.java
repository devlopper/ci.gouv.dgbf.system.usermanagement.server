package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByString;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=UserFunction.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=UserFunction.UNIQUE_CONSTRAINT_USER_FUNCTION_NAME,columnNames= {UserFunction.COLUMN_USER,UserFunction.COLUMN_FUNCTION}
		)})
public class UserFunction extends AbstractIdentifiedByString implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_USER) @NotNull private User user;
	@ManyToOne @JoinColumn(name=COLUMN_FUNCTION) @NotNull private Function function;
	
	/**/
	
	public UserFunction setUserByIdentifier(String identifier) {
		__setFromSystemIdentifier__(FIELD_USER, identifier);
		return this;
	}
	
	public UserFunction setFunctionByCode(String code) {
		__setFromBusinessIdentifier__(FIELD_FUNCTION, code);
		return this;
	}
	
	/**/
	
	public static final String FIELD_USER = "user";
	public static final String FIELD_FUNCTION = "function";
	
	public static final String TABLE_NAME = User.TABLE_NAME+Function.TABLE_NAME;
	
	public static final String COLUMN_USER = User.TABLE_NAME;
	public static final String COLUMN_FUNCTION = Function.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_USER_FUNCTION_NAME = COLUMN_USER+COLUMN_FUNCTION;
	
}
