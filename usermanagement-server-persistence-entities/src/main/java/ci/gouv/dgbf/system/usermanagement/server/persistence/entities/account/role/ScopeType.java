package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.server.persistence.hierarchy.AbstractIdentifiedByStringAndCodedAndNamedAndHierarchical;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=ScopeType.TABLE_NAME)
public class ScopeType extends AbstractIdentifiedByStringAndCodedAndNamedAndHierarchical<ScopeType,ScopeTypes> implements Serializable {
	private static final long serialVersionUID = 1L;

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
	
	/**/

	public static final String TABLE_NAME = "typ"+Scope.TABLE_NAME;
	
	/**/
	
	public static final String CODE_UGP = "UGP";
	public static final String CODE_UA = "UA";
	public static final String CODE_SECTION = "SECTION";
	public static final String CODE_ACTE_BUDGETAIRE = "ACTE_BUDGETAIRE";
	
}
