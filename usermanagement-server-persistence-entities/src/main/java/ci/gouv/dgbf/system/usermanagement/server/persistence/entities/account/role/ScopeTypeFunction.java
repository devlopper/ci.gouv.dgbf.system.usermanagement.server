package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=ScopeTypeFunction.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=ScopeTypeFunction.UNIQUE_CONSTRAINT_SCOPE_TYPE_FUNCTION_NAME,columnNames= {ScopeTypeFunction.COLUMN_SCOPE_TYPE,ScopeTypeFunction.COLUMN_FUNCTION}
		)}) @NoArgsConstructor
public class ScopeTypeFunction extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_SCOPE_TYPE) private ScopeType scopeType;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_FUNCTION) private Function function;
	@NotNull @Column(name = COLUMN_FUNCTION_CREATABLE_ON_SCOPE_CREATE) private Boolean functionCreatableOnScopeCreate;
	
	/**/
	
	public ScopeTypeFunction(ScopeType scopeType,Function profile,Boolean functionCreatableOnScopeCreate) {
		this.scopeType = scopeType;
		this.function = profile;
		this.functionCreatableOnScopeCreate = functionCreatableOnScopeCreate;
	}
	
	public ScopeTypeFunction setScopeTypeFromCode(String code) {
		if(StringHelper.isBlank(code))
			return this;
		this.scopeType = InstanceGetter.getInstance().getByBusinessIdentifier(ScopeType.class, code);
		return this;
	}
	
	public ScopeTypeFunction setFunctionFromCode(String code) {
		if(StringHelper.isBlank(code))
			return this;
		this.function = InstanceGetter.getInstance().getByBusinessIdentifier(Function.class, code);
		return this;
	}
	
	/**/

	public static final String FIELD_SCOPE_TYPE = "scopeType";
	public static final String FIELD_FUNCTION = "function";
	public static final String FIELD_FUNCTION_CREATABLE_ON_SCOPE_CREATE = "functionCreatableOnScopeCreate";
	
	public static final String COLUMN_SCOPE_TYPE = ScopeType.TABLE_NAME;
	public static final String COLUMN_FUNCTION = Function.TABLE_NAME;
	public static final String COLUMN_FUNCTION_CREATABLE_ON_SCOPE_CREATE = "creer_fct_auto";
	
	public static final String TABLE_NAME = ScopeType.TABLE_NAME+Function.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_SCOPE_TYPE_FUNCTION_NAME = COLUMN_SCOPE_TYPE+COLUMN_FUNCTION;
}
