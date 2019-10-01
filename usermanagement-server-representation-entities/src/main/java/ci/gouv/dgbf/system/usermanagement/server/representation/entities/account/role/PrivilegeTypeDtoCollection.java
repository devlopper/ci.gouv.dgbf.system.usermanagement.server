package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractCollectionOfIdentifiedByStringAndCodedAndNamedImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@XmlSeeAlso(PrivilegeTypeDto.class)
public class PrivilegeTypeDtoCollection extends AbstractCollectionOfIdentifiedByStringAndCodedAndNamedImpl<PrivilegeTypeDto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<PrivilegeTypeDto> elements;
	
}
