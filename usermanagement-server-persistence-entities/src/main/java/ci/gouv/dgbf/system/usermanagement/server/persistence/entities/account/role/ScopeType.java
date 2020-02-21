package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=ScopeType.TABLE_NAME)
public class ScopeType extends AbstractIdentifiedByStringAndCodedAndNamed<ScopeType> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient private Collection<Function> functions;
	
	@Override
	public ScopeType addParents(ScopeType... parents) {
		return (ScopeType) super.addParents(parents);
	}
	
	@Override
	public ScopeType setIdentifier(String identifier) {
		return (ScopeType) super.setIdentifier(identifier);
	}
	
	@Override
	public ScopeType setCode(String code) {
		return (ScopeType) super.setCode(code);
	}
	
	@Override
	public ScopeType setName(String name) {
		return (ScopeType) super.setName(name);
	}
	
	public ScopeType addFunctions(Collection<Function> functions) {
		if(CollectionHelper.isEmpty(functions))
			return this;
		Collection<Function> __functions__ = functions.stream().filter(x -> x != null).collect(Collectors.toList());
		if(CollectionHelper.isEmpty(__functions__))
			return this;
		if(this.functions == null)
			this.functions = new ArrayList<>();
		this.functions.addAll(__functions__);
		return this;
	}
	
	public ScopeType addFunctions(Function...functions) {
		if(ArrayHelper.isEmpty(functions))
			return this;
		return addFunctions(CollectionHelper.listOf(functions));
	}
	
	public ScopeType addFunctionsByCodes(Collection<String> functionsCodes) {
		if(CollectionHelper.isEmpty(functionsCodes))
			return this;
		for(String index : functionsCodes)
			addFunctions(InstanceGetter.getInstance().getByBusinessIdentifier(Function.class, index));
		return this;
	}
	
	public ScopeType addFunctionsByCodes(String...functionsCodes) {
		if(ArrayHelper.isEmpty(functionsCodes))
			return this;
		return addFunctionsByCodes(CollectionHelper.listOf(functionsCodes));
	}
	
	/**/

	public static final String FIELD_FUNCTIONS = "functions";
	
	public static final String TABLE_NAME = "typ"+Scope.TABLE_NAME;
	
	/**/
	
	public static final String CODE_UGP = "UGP";
	public static final String CODE_UA = "UA";
	public static final String CODE_SECTION = "SECTION";
	public static final String CODE_ACTE_BUDGETAIRE = "ACTE_BUDGETAIRE";
	
}
