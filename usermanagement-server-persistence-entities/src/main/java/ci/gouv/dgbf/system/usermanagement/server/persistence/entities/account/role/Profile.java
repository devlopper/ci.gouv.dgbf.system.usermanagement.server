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

import org.cyk.utility.server.persistence.jpa.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD)
@Table(name=Profile.TABLE_NAME)
public class Profile extends AbstractIdentifiedByStringAndCodedAndNamed implements FieldContainerFunctions,FieldContainerPrivileges,Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_TYPE) @NotNull private ProfileType type;
	
	/*
	 * The functions covered by this profile
	 */
	@Transient private Collection<Function> functions;
	
	/*
	 * The privileges associated to this profile
	 */
	@Transient private Collection<Privilege> privileges;
	
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
		__setFromBusinessIdentifier__(FIELD_TYPE, code);
		return this;
	}
	
	@Override
	public Profile addFunctions(Function... functions) {
		return (Profile) FieldContainerFunctions.super.addFunctions(functions);
	}
	
	@Override
	public Profile addFunctionsByCodes(String... codes) {
		return (Profile) FieldContainerFunctions.super.addFunctionsByCodes(codes);
	}
	
	/**/
	
	public static final String FIELD_TYPE = "type";
	
	public static final String TABLE_NAME = "pfl";
	
	public static final String COLUMN_TYPE = "type";
	
	/**/
	
}
