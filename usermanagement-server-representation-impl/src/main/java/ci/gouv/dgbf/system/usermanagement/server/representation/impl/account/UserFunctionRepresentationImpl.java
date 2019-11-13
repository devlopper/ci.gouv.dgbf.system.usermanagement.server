package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserFunctionDto;

@ApplicationScoped
public class UserFunctionRepresentationImpl extends AbstractRepresentationEntityImpl<UserFunctionDto> implements UserFunctionRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
