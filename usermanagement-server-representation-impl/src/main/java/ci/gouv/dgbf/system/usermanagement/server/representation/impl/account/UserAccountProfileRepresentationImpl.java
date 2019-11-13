package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountProfileRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountProfileDto;

@ApplicationScoped
public class UserAccountProfileRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountProfileDto> implements UserAccountProfileRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
