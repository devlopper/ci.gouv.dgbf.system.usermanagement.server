package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.cyk.utility.server.representation.AbstractEntityCollection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@XmlSeeAlso(PrivilegeDto.class)
public class PrivilegeDtoCollection extends AbstractEntityCollection<PrivilegeDto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
}