package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProfilePrivilegeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProfilePrivilegeDto;

@ApplicationScoped
public class ProfilePrivilegeRepresentationImpl extends AbstractRepresentationEntityImpl<ProfilePrivilegeDto> implements ProfilePrivilegeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;


}
