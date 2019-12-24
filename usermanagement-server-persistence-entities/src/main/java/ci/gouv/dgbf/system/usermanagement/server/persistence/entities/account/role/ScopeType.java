package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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

	@Transient private Collection<Profile> profiles;
	
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
	
	public ScopeType addProfiles(Collection<Profile> profiles) {
		if(CollectionHelper.isEmpty(profiles))
			return this;
		if(this.profiles == null)
			this.profiles = new ArrayList<>();
		this.profiles.addAll(profiles);
		return this;
	}
	
	public ScopeType addProfiles(Profile...profiles) {
		if(ArrayHelper.isEmpty(profiles))
			return this;
		return addProfiles(CollectionHelper.listOf(profiles));
	}
	
	public ScopeType addProfilesByCodes(Collection<String> profilesCodes) {
		if(CollectionHelper.isEmpty(profilesCodes))
			return this;
		for(String index : profilesCodes)
			addProfiles(InstanceGetter.getInstance().getByBusinessIdentifier(Profile.class, index));
		return this;
	}
	
	public ScopeType addProfilesByCodes(String...profilesCodes) {
		if(ArrayHelper.isEmpty(profilesCodes))
			return this;
		return addProfilesByCodes(CollectionHelper.listOf(profilesCodes));
	}
	
	/**/

	public static final String FIELD_PROFILES = "profiles";
	
	public static final String TABLE_NAME = "typ"+Scope.TABLE_NAME;
	
	/**/
	
	public static final String CODE_UGP = "UGP";
	public static final String CODE_UA = "UA";
	public static final String CODE_SECTION = "SECTION";
	public static final String CODE_ACTE_BUDGETAIRE = "ACTE_BUDGETAIRE";
	
}
