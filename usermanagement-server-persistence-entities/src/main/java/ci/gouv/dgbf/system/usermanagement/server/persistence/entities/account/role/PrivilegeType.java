package ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.server.persistence.jpa.hierarchy.AbstractIdentifiedByStringAndCodedAndNamed;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity @Getter @Setter @Accessors(chain=true) @Access(AccessType.FIELD) @ToString
@Table(name=PrivilegeType.TABLE_NAME)
public class PrivilegeType extends AbstractIdentifiedByStringAndCodedAndNamed<PrivilegeType,PrivilegeTypes> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public PrivilegeType addParents(PrivilegeType... children) {
		return (PrivilegeType) super.addParents(children);
	}
	
	@Override
	public PrivilegeType setIdentifier(String identifier) {
		return (PrivilegeType) super.setIdentifier(identifier);
	}
	
	@Override
	public PrivilegeType setCode(String code) {
		return (PrivilegeType) super.setCode(code);
	}
	
	@Override
	public PrivilegeType setName(String name) {
		return (PrivilegeType) super.setName(name);
	}
	
	/**/
	
	/**/
	
	public static final String TABLE_NAME = "typ"+Privilege.TABLE_NAME;
	
	/**/
	
	public static final String CODE_MODULE = "MODULE";
	public static final String CODE_SERVICE = "SERVICE";
	public static final String CODE_MENU = "MENU";
	public static final String CODE_ACTION = "ACTION";
	
}
