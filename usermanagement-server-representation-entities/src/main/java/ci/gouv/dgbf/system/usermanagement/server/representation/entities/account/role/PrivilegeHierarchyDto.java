package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.__kernel__.object.__static__.representation.hierarchy.AbstractHierarchyByStringImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class PrivilegeHierarchyDto extends AbstractHierarchyByStringImpl<PrivilegeDto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private PrivilegeDto parent;
	private PrivilegeDto child;
	
}
