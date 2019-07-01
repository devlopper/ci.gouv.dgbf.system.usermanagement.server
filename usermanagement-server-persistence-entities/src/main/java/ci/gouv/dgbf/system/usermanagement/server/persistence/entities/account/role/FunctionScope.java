package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=FunctionScope.TABLE_NAME)
public class FunctionScope extends AbstractIdentifiedByStringAndCodedAndNamed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_FUNCTION) @NotNull private Function function;	
	@ManyToOne @JoinColumn(name=COLUMN_SCOPE) @NotNull private Scope scope;
	
	/**/
	
	@Override
	public FunctionScope setIdentifier(String identifier) {
		return (FunctionScope) super.setIdentifier(identifier);
	}
	
	@Override
	public FunctionScope setCode(String code) {
		return (FunctionScope) super.setCode(code);
	}
	
	@Override
	public FunctionScope setName(String name) {
		return (FunctionScope) super.setName(name);
	}
	
	/**/
	
	public static final String FIELD_FUNCTION = "function";
	public static final String FIELD_SCOPE = "scope";
	
	public static final String TABLE_NAME = Function.TABLE_NAME+Scope.TABLE_NAME;
	
	public static final String COLUMN_FUNCTION = Function.TABLE_NAME;
	public static final String COLUMN_SCOPE = Scope.TABLE_NAME;
	
}
