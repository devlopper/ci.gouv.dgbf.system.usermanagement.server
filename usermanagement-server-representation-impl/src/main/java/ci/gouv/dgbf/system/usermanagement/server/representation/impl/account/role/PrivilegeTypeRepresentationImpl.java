package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.PrivilegeTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.PrivilegeTypeDto;

@ApplicationScoped
public class PrivilegeTypeRepresentationImpl extends AbstractRepresentationEntityImpl<PrivilegeTypeDto> implements PrivilegeTypeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
